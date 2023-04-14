package refappintegrasjon.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import refappintegrasjon.consumer.LosoreTlgBestandConsumer;

import java.time.ZonedDateTime;

import static java.lang.String.format;

@Slf4j
@Service
public class LosoreTlgBestandService {

    private final LosoreTlgBestandConsumer losoreTlgBestandConsumer;

    public LosoreTlgBestandService(LosoreTlgBestandConsumer losoreTlgBestandConsumer) {
        this.losoreTlgBestandConsumer = losoreTlgBestandConsumer;
    }

    public ZonedDateTime prosesserTotalbestand() throws Exception {
        var upperCutoff = ZonedDateTime.now();
        Object[] lastSortValues = null;
        var lastPage = false;
        while (!lastPage) {
            var response = losoreTlgBestandConsumer.totalbestand(upperCutoff, lastSortValues);
            lastSortValues = response.getSortValues();
            lastPage = response.getRettsstiftelse().isEmpty();
            log.info(format("Hentet %s rettsstiftelser fra totalbestand", response.getAntallRettsstiftelser()));
            // TODO: oppdater lokal kopi av rettsstiftelser
        }

        return upperCutoff;
    }

    public ZonedDateTime prosesserEndringslogg(ZonedDateTime lowerCutoff) throws Exception {
        var sistEndretSisteInnslag = ZonedDateTime.now();
        Object[] lastSortValues = null;
        var lastPage = false;
        while (!lastPage) {
            var response = losoreTlgBestandConsumer.endringslogg(lowerCutoff, lastSortValues);
            lastSortValues = response.getSortValues();
            lastPage = response.getRettsstiftelse().isEmpty();
            sistEndretSisteInnslag = response.getSistEndretSisteInnslag();
            log.info(format("Hentet %s rettsstiftelser fra endringslogg", response.getAntallRettsstiftelser()));
            // TODO: oppdater lokal kopi av rettsstiftelser
        }

        return sistEndretSisteInnslag;
    }
}
