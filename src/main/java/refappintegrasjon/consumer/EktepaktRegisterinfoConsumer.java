package refappintegrasjon.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import refappintegrasjon.dto.ektepakt.Oppslag;

import java.net.URI;

@Slf4j
@Component
public class EktepaktRegisterinfoConsumer {

    private static final String REGISTRATION_ID = "ektepakt-registerinfo";
    private final WebClient webClient;
    private final String host;

    public EktepaktRegisterinfoConsumer(WebClient webClient,
                                        @Value("endpoint.ektepakt") String host) {
        this.webClient = webClient;
        this.host = host;
    }

    public Mono<Oppslag> getEktepaktByFnr(String fnr) {
        return webClient.get()
                .uri(ektepaktUri(fnr))
                .accept(MediaType.APPLICATION_JSON)
                .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId(REGISTRATION_ID))
                .retrieve()
                .bodyToMono(Oppslag.class);
    }

    private URI ektepaktUri(String fnr) {
        return UriComponentsBuilder.fromUriString(host)
                .path("api/v1/fnr/")
                .path(fnr)
                .build()
                .toUri();
    }
}
