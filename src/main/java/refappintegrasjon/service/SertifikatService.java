package refappintegrasjon.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import refappintegrasjon.exceptions.SertifikatException;

import java.io.ByteArrayInputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
public class SertifikatService {


    private final String privateKey;
    private final String certificate;

    private static final String PK_PREFIX = "-----BEGIN PRIVATE KEY-----";
    private static final String PK_SUFFIX = "-----END PRIVATE KEY-----";
    private static final String CERTIFICATE_PREFIX = "-----BEGIN CERTIFICATE-----";
    private static final String CERTIFICATE_SUFFIX = "-----END CERTIFICATE-----";
    private static final String FACTORY_TYPE = "X.509";
    private static final String CRYPTOSYSTEM = "RSA";

    public SertifikatService(@Value("${virksomhet.privateKey}") String privateKey, @Value("${virksomhet.certificate}") String certificate) {
        this.privateKey = privateKey;
        this.certificate = certificate;
    }

    public PrivateKey getPrivateKey() throws SertifikatException {
        String privateKeyContent = privateKey
                .replaceAll("\\n", "")
                .replace(PK_PREFIX, "")
                .replace(PK_SUFFIX, "")
                .replaceAll("\\s+", "");

        try {
            return KeyFactory
                    .getInstance(CRYPTOSYSTEM)
                    .generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent)));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new SertifikatException("klarte ikke å generere privat nøkkel fra virksomhetssertifikat", e);
        }
    }

    public X509Certificate getX509Certificate() throws SertifikatException {
        String certContent = certificate
                .replaceAll("\\n", "")
                .replace(CERTIFICATE_PREFIX, "")
                .replace(CERTIFICATE_SUFFIX, "")
                .replaceAll("\\s+", "");

        try {
            return (X509Certificate) CertificateFactory
                    .getInstance(FACTORY_TYPE)
                    .generateCertificate(new ByteArrayInputStream(Base64.getDecoder().decode(certContent)));

        } catch (CertificateException e) {
            throw new SertifikatException("Klarte ikke å generere sertifikat fra virksomhetssertifikat", e);
        }
    }
}
