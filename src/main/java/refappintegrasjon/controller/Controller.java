package refappintegrasjon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import refappintegrasjon.consumer.EndpointProtectedByMaskinportenConsumer;

@Slf4j
@RestController
public class Controller {

    private final EndpointProtectedByMaskinportenConsumer endpointProtectedByMaskinportenConsumer;

    public Controller(EndpointProtectedByMaskinportenConsumer endpointProtectedByMaskinportenConsumer) {
        this.endpointProtectedByMaskinportenConsumer = endpointProtectedByMaskinportenConsumer;
    }

    @GetMapping("test/maskinportintegrasjon")
    public ResponseEntity<String> callConsumer() {
        return endpointProtectedByMaskinportenConsumer.callEnpointProtectedByMaskinporten();
    }
}
