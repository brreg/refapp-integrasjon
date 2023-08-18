package refappintegrasjon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import refappintegrasjon.dto.ektepakt.Oppslag;
import refappintegrasjon.service.EktepaktTlgService;

@Slf4j
@RestController
public class EktepaktController {

    private final EktepaktTlgService ektepaktTlgService;

    public EktepaktController(EktepaktTlgService ektepaktTlgService) {
        this.ektepaktTlgService = ektepaktTlgService;
    }

    @GetMapping("test/ektepakt/fnr/{fnr}")
    public Oppslag testEktepaktFnr(@PathVariable String fnr) {
        return ektepaktTlgService.getEktepaktbyFnr(fnr).block();
    }
}
