package refappintegrasjon.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import refappintegrasjon.consumer.EktepaktRegisterinfoConsumer;
import refappintegrasjon.dto.ektepakt.Oppslag;

@Service
public class EktepaktTlgService {

    private final EktepaktRegisterinfoConsumer ektepaktRegisterinfoConsumer;

    public EktepaktTlgService(EktepaktRegisterinfoConsumer ektepaktRegisterinfoConsumer) {
        this.ektepaktRegisterinfoConsumer = ektepaktRegisterinfoConsumer;
    }

    public Mono<Oppslag> getEktepaktbyFnr(String fnr) {
        return ektepaktRegisterinfoConsumer.getEktepaktByFnr(fnr);
    }
}
