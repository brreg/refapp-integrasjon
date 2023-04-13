package refappintegrasjon.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import refappintegrasjon.dto.oppslag.RettsstiftelseV2Wrapper;

import java.net.URI;

import static java.lang.String.format;

@Slf4j
@Component
public class LosoreTlgOppslagConsumer {

    private static final String BEARER_PREFIX = "Bearer ";

    private final MaskinportenTokenConsumer maskinportenTokenConsumer;
    private final String host;
    private final WebClient webClient;

    public LosoreTlgOppslagConsumer(MaskinportenTokenConsumer maskinportenTokenConsumer,
                                    @Value("${endpoint.url}") String host,
                                    WebClient webClient) {
        this.maskinportenTokenConsumer = maskinportenTokenConsumer;
        this.host = host;
        this.webClient = webClient;
    }

    public RettsstiftelseV2Wrapper oppslagRegistreringsnummer(String registreringsnummer) throws Exception {
        return webClient.get()
                .uri(byggUriOppslag("regnr", registreringsnummer, null))
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + maskinportenTokenConsumer.hentToken())
                .retrieve()
                .bodyToMono(RettsstiftelseV2Wrapper.class)
                .doOnError(t -> log.error(
                        format("Feil ved oppslag på registreringsnummer %s: %s", registreringsnummer, t.getMessage()), t))
                .block();
    }

    public RettsstiftelseV2Wrapper oppslagVirksomhet(String organisasjonsnummer, String sluttbrukerOrgNr) throws Exception {
        return webClient.get()
                .uri(byggUriOppslag("orgnr", organisasjonsnummer, sluttbrukerOrgNr))
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + maskinportenTokenConsumer.hentToken())
                .retrieve()
                .bodyToMono(RettsstiftelseV2Wrapper.class)
                .doOnError(t -> log.error(
                        format("Feil ved oppslag på organisasjonsnummer %s: %s", organisasjonsnummer, t.getMessage()), t))
                .block();
    }

    public RettsstiftelseV2Wrapper oppslagPerson(String fnrEllerDnr, String sluttbrukerOrgNr) throws Exception {
        return webClient.get()
                .uri(byggUriOppslag("fnr", fnrEllerDnr, sluttbrukerOrgNr))
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + maskinportenTokenConsumer.hentToken())
                .retrieve()
                .bodyToMono(RettsstiftelseV2Wrapper.class)
                .doOnError(t -> log.error(
                        format("Feil ved oppslag på person %s: %s", fnrEllerDnr, t.getMessage()), t))
                .block();
    }

    private URI byggUriOppslag(String typeOppslag, String id, String sluttbrukerOrgNr) {
        var uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(host)
                .path("/api/v2/rettsstiftelse/")
                .pathSegment(typeOppslag, id);

        if (sluttbrukerOrgNr != null) {
            uriComponentsBuilder.queryParam("sluttbrukerOrgNr", sluttbrukerOrgNr);
        }

        return uriComponentsBuilder
                .build()
                .toUri();
    }
}
