package refappintegrasjon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import refappintegrasjon.consumer.EndpointProtectedByMaskinportenConsumer;

@Slf4j
@RestController
public class Controller {

    private EndpointProtectedByMaskinportenConsumer endpointProtectedByMaskinportenConsumer;

    public Controller(EndpointProtectedByMaskinportenConsumer endpointProtectedByMaskinportenConsumer) {
        this.endpointProtectedByMaskinportenConsumer = endpointProtectedByMaskinportenConsumer;
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "test/maskinportintegrasjon")
    public ResponseEntity<String> callConsumer() {
        return endpointProtectedByMaskinportenConsumer.callEnpointProtectedByMaskinporten();
    }
}
