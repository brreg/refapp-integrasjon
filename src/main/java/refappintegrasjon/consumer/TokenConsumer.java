package refappintegrasjon.consumer;

import lombok.extern.slf4j.Slf4j;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import refappintegrasjon.exceptions.JwtException;
import refappintegrasjon.exceptions.SertifikatException;
import refappintegrasjon.service.SertifikatService;

@Slf4j
@Component
public class TokenConsumer {

    private static final int EXP_IN_MIN = 2;
    private static final int NBF_IN_MIN = 2;

    private final String audience;
    private final String tokenEndpoint;
    private final RestTemplate restTemplate;
    private final SertifikatService sertifikatService;

    public TokenConsumer(@Value("${jwt.audience}") String audience, @Value("${jwt.endpoint}") String tokenEndpoint,
                         RestTemplate restTemplate, SertifikatService sertifikatService) {
        this.audience = audience;
        this.tokenEndpoint = tokenEndpoint;
        this.restTemplate = restTemplate;
        this.sertifikatService = sertifikatService;
    }

    public String hentToken(String scope, String klient) throws JwtException {
        return makeTokenRequest(makeJwt(scope, klient)).get("access_token").toString();
    }

    private String makeJwt(String scope, String klient) throws JwtException {
        try {
            JwtClaims claims = new JwtClaims();
            claims.setIssuer(klient);
            claims.setAudience(audience);
            claims.setExpirationTimeMinutesInTheFuture(EXP_IN_MIN);
            claims.setGeneratedJwtId();
            claims.setIssuedAtToNow();
            claims.setNotBeforeMinutesInThePast(NBF_IN_MIN);
            claims.setClaim("scope", scope);

            JsonWebSignature jws = new JsonWebSignature();
            jws.setPayload(claims.toJson());
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
            jws.setCertificateChainHeaderValue(sertifikatService.getX509Certificate());
            jws.setKey(sertifikatService.getPrivateKey());

            return jws.getCompactSerialization();
        } catch (JoseException | SertifikatException e) {
            throw new JwtException("Feil oppsto i generering av JWT", e);
        }
    }

    private JSONObject makeTokenRequest(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "urn:ietf:params:oauth:grant-type:jwt-bearer");
        map.add("assertion", jwt);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return restTemplate.postForObject(tokenEndpoint, request, JSONObject.class);
    }
}
