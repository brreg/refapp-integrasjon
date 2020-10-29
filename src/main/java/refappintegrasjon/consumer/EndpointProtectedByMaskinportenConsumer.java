package refappintegrasjon.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import refappintegrasjon.exceptions.JwtException;

import java.net.URI;

@Slf4j
@Component
public class EndpointProtectedByMaskinportenConsumer {

    private final RestTemplate restTemplate;
    private final TokenConsumer tokenConsumer;

    @Value("${endpoint.url}")
    private String host;
    @Value("${token.client")
    private String client;
    @Value("${token.scope")
    private String scope;

    private String orgnummer = "810727462";

    public EndpointProtectedByMaskinportenConsumer(RestTemplate restTemplate, TokenConsumer tokenConsumer) {
        this.restTemplate = restTemplate;
        this.tokenConsumer = tokenConsumer;
    }

    public ResponseEntity<String> callEnpointProtectedByMaskinporten() {
        try {
            URI uri = UriComponentsBuilder
                    .fromUriString(host)
                    .path("/api/v1/rettsstiftelse/orgnr/")
                    .pathSegment(orgnummer)
                    .build()
                    .toUri();
            return restTemplate.exchange(uri,
                    HttpMethod.GET,
                    createRequestEntity(), String.class);
        } catch (HttpClientErrorException e) {
            log.error("Noe gikk galt i kallet mot endepunkt" + e);
            throw new RuntimeException();
        } catch (JwtException e) {
            log.error("Noe gikk galt under henting av jwt token" + e);
            throw new RuntimeException();
        }
    }

    private HttpEntity<Void> createRequestEntity() throws JwtException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tokenConsumer.hentToken(scope, client));
        return new HttpEntity<>(headers);
    }
}
