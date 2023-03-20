package refappintegrasjon.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.lang.String.format;

@Slf4j
@Component
public class LosoreTlgConsumer {

    private final MaskinportenTokenConsumer maskinportenTokenConsumer;
    private final String host;
    private final WebClient webClient;
    private String orgnummer = "811087432";

    public LosoreTlgConsumer(MaskinportenTokenConsumer maskinportenTokenConsumer,
                             @Value("${endpoint.url}") String host,
                             WebClient webClient) {
        this.maskinportenTokenConsumer = maskinportenTokenConsumer;
        this.host = host;
        this.webClient = webClient;
    }

    public JsonNode oppslagVirksomhet() throws Exception {
        return webClient.get()
                .uri(createRettsstiftelseUri(orgnummer))
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + maskinportenTokenConsumer.hentToken())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .doOnError(t -> log.error(
                        format("Feil oppstod ved henting av aktiv rettsstiftelse for dokumentnummer %s fra losore-database-api", orgnummer), t))
                .block();
    }

    private URI createRettsstiftelseUri(String orgnummer) {
        return UriComponentsBuilder
                .fromUriString(host)
                .path("/api/v1/rettsstiftelse/orgnr/").pathSegment(orgnummer)
                .build()
                .toUri();
    }
}
