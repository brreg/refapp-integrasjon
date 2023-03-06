package refappintegrasjon.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.time.Clock;
import java.util.*;

@Slf4j
@Component
public class MaskinportenTokenConsumer {
    private final String issuer;
    private final String audience;
    private final String tokenEndpoint;
    private final String scope;
    private final WebClient webClient;
    X509Certificate certificate;
    PrivateKey privateKey;
    ObjectMapper mapper;

    public MaskinportenTokenConsumer(@Value("${issuer}") String issuer,
                                     @Value("${audience}") String audience,
                                     @Value("${token.endpoint}") String tokenEndpoint,
                                     @Value("${scope}") String scope,
                                     @Value("${keystore.file}") String keystoreFile,
                                     @Value("${keystore.password}") String keystorePassword,
                                     @Value("${keystore.alias}") String keystoreAlias,
                                     @Value("${keystore.alias.password}") String keystoreAliasPassword,
                                     @Value("${keystore.type}") String keystoreType,
                                     WebClient webClient,
                                     ObjectMapper mapper) throws Exception {
        this.issuer = issuer;
        this.audience = audience;
        this.tokenEndpoint = tokenEndpoint;
        this.scope = scope;
        this.webClient = webClient;
        this.mapper = mapper;
        loadCertificateAndKeyFromFile(keystoreType, keystoreFile, keystorePassword, keystoreAlias, keystoreAliasPassword);
    }

    public String hentToken() throws Exception {
        String jwt = makeJwt();

        JsonNode jsonNode = mapper.readValue(makeTokenRequest(jwt), JsonNode.class);
        return jsonNode.get("access_token").asText();
    }

    private String makeJwt() throws Exception {

        List<Base64> certChain = new ArrayList<>();
        certChain.add(Base64.encode(certificate.getEncoded()));

        JWSHeader jwtHeader;
        jwtHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .x509CertChain(certChain)
                .build();

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .audience(audience)
                .issuer(issuer)
                .claim("scope", scope)
                .jwtID(UUID.randomUUID().toString()) // Must be unique for each grant
                .issueTime(new Date(Clock.systemUTC().millis())) // Use UTC time!
                .expirationTime(new Date(Clock.systemUTC().millis() + 120000)) // Expiration time is 120 sec.
                .build();

        JWSSigner signer = new RSASSASigner(privateKey);
        SignedJWT signedJWT = new SignedJWT(jwtHeader, claims);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    private String makeTokenRequest(String jwt) {
        return webClient.post()
                .uri(tokenEndpoint)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters
                        .fromFormData("grant_type", "urn:ietf:params:oauth:grant-type:jwt-bearer")
                        .with("assertion", jwt))
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(error ->
                        log.error(error.getMessage(), error))
                .block();
    }

    private void loadCertificateAndKeyFromFile(String keystoreType, String keyStoreFile, String keyStorePassword, String alias, String keyPassword) throws Exception {
        final InputStream is;
        if (keyStoreFile.startsWith("base64:")) {
            is = new ByteArrayInputStream(java.util.Base64.getDecoder().decode(keyStoreFile.replace("base64:", "")));
        } else {
            is = new FileInputStream(keyStoreFile);
        }
        loadCertificate(is, keystoreType, keyStorePassword, alias, keyPassword);
    }

    private void loadCertificate(InputStream is, String keystoreType, String keystorePassword, String alias, String keyPassword) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(keystoreType);
        keyStore.load(is, keystorePassword.toCharArray());

        privateKey = (PrivateKey) keyStore.getKey(alias, keyPassword.toCharArray()); // Read from KeyStore
        certificate = (X509Certificate) keyStore.getCertificate(alias);
    }
}
