package refappintegrasjon.service;

import org.springframework.stereotype.Service;
import refappintegrasjon.consumer.LosoreTlgOppslagConsumer;
import refappintegrasjon.dto.losore.oppslag.RettsstiftelseV2Wrapper;

@Service
public class LosoreTlgOppslagService {

    private final LosoreTlgOppslagConsumer losoreTlgOppslagConsumer;

    public LosoreTlgOppslagService(LosoreTlgOppslagConsumer losoreTlgOppslagConsumer) {
        this.losoreTlgOppslagConsumer = losoreTlgOppslagConsumer;
    }

    public RettsstiftelseV2Wrapper oppslagRegistreringsnummer(String registreringsnummer) throws Exception {
        return losoreTlgOppslagConsumer.oppslagRegistreringsnummer(registreringsnummer);
    }

    public RettsstiftelseV2Wrapper oppslagVirksomhet(String organisasjonsnummer, String sluttbrukerOrgNr) throws Exception {
        return losoreTlgOppslagConsumer.oppslagVirksomhet(organisasjonsnummer, sluttbrukerOrgNr);
    }

    public RettsstiftelseV2Wrapper oppslagPerson(String fnrEllerDnr, String sluttbrukerOrgNr) throws Exception {
        return losoreTlgOppslagConsumer.oppslagPerson(fnrEllerDnr, sluttbrukerOrgNr);
    }
}
