package refappintegrasjon.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import refappintegrasjon.consumer.LosoreTlgConsumer;

@Slf4j
@RestController
public class Controller {

    private final LosoreTlgConsumer losoreTlgConsumer;

    public Controller(LosoreTlgConsumer losoreTlgConsumer) {
        this.losoreTlgConsumer = losoreTlgConsumer;
    }

    @GetMapping("test/maskinportintegrasjon")
    public JsonNode callConsumer() throws Exception {
        return losoreTlgConsumer.oppslagVirksomhet();
    }
}
