package refappintegrasjon.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import refappintegrasjon.dto.endringslogg.EndringsloggRequest;
import refappintegrasjon.dto.endringslogg.EndringsloggResponse;
import refappintegrasjon.dto.totalbestand.TotalbestandRequest;
import refappintegrasjon.dto.totalbestand.TotalbestandResponse;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static java.lang.String.format;

@Slf4j
@Component
public class LosoreTlgBestandConsumer {

    private static final String BEARER_PREFIX = "Bearer ";

    private final MaskinportenTokenConsumer maskinportenTokenConsumer;
    private final String host;
    private final WebClient webClient;

    public LosoreTlgBestandConsumer(MaskinportenTokenConsumer maskinportenTokenConsumer,
                                    @Value("${endpoint.url}") String host,
                                    WebClient webClient) {
        this.maskinportenTokenConsumer = maskinportenTokenConsumer;
        this.host = host;
        this.webClient = webClient;
    }

    public TotalbestandResponse totalbestand(ZonedDateTime upperCutoff, Object[] lastSortValues) throws Exception {
        var totalbestandRequest = TotalbestandRequest.builder()
                .upperCutoff(upperCutoff)
                .lastSortValues(lastSortValues)
                .build();

        return webClient.post()
                .uri(byggUriBestand("totalbestand"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(totalbestandRequest)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + maskinportenTokenConsumer.hentToken())
                .retrieve()
                .bodyToMono(TotalbestandResponse.class)
                .doOnError(t -> {
                    var message = t.getMessage();
                    if (t instanceof WebClientResponseException webClientResponseException) {
                        message = webClientResponseException.getResponseBodyAsString();
                    }
                    log.error(format("Feil ved henting av totalbestand med upperCutoff %s og lastSortValues %s: %s",
                                    upperCutoff, Arrays.toString(lastSortValues), message), t);
                })
                .block();
    }

    public EndringsloggResponse endringslogg(ZonedDateTime lowerCutoff, Object[] lastSortValues) throws Exception {
        var endringsloggRequest = EndringsloggRequest.builder()
                .lowerCutoff(lowerCutoff)
                .lastSortValues(lastSortValues)
                .build();

        return webClient.post()
                .uri(byggUriBestand("endringslogg"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(endringsloggRequest)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + maskinportenTokenConsumer.hentToken())
                .retrieve()
                .bodyToMono(EndringsloggResponse.class)
                .doOnError(t -> {
                    var message = t.getMessage();
                    if (t instanceof WebClientResponseException webClientResponseException) {
                        message = webClientResponseException.getResponseBodyAsString();
                    }
                    log.error(format("Feil ved henting av endringslogg med lowerCutoff %s og lastSortValues %s: %s",
                            lowerCutoff, Arrays.toString(lastSortValues), message), t);
                })
                .block();
    }

    private URI byggUriBestand(String typeBestand) {
        return UriComponentsBuilder
                .fromUriString(host)
                .path("/api/v2/rettsstiftelse")
                .pathSegment(typeBestand)
                .queryParam("language", "NOB")
                .build()
                .toUri();
    }
}
