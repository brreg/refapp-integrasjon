package refappintegrasjon.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import refappintegrasjon.dto.oppslag.RettsstiftelseV2Wrapper;
import refappintegrasjon.service.LosoreTlgBestandService;
import refappintegrasjon.service.LosoreTlgOppslagService;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
public class Controller {

    private final LosoreTlgOppslagService losoreTlgOppslagService;
    private final LosoreTlgBestandService losoreTlgBestandService;

    public Controller(LosoreTlgOppslagService losoreTlgOppslagService,
                      LosoreTlgBestandService losoreTlgBestandService) {
        this.losoreTlgOppslagService = losoreTlgOppslagService;
        this.losoreTlgBestandService = losoreTlgBestandService;
    }

    @GetMapping("test/regnr/{regnr}")
    public RettsstiftelseV2Wrapper testRegistreringsnummer(@PathVariable String regnr) throws Exception {
        return losoreTlgOppslagService.oppslagRegistreringsnummer(regnr);
    }

    @GetMapping("test/orgnr/{orgnr}")
    public RettsstiftelseV2Wrapper testOrgnr(@PathVariable String orgnr,
                                             @RequestParam(value = "sluttbrukerOrgNr", required = false) String sluttbrukerOrgNr) throws Exception {
        return losoreTlgOppslagService.oppslagVirksomhet(orgnr, sluttbrukerOrgNr);
    }

    @GetMapping("test/fnr/{fnr}")
    public RettsstiftelseV2Wrapper testFnr(@PathVariable String fnr,
                                           @RequestParam(value = "sluttbrukerOrgNr", required = false) String sluttbrukerOrgNr) throws Exception {
        return losoreTlgOppslagService.oppslagPerson(fnr, sluttbrukerOrgNr);
    }

    @GetMapping("test/totalbestand")
    public JsonNode testTotalbestand() throws Exception {
        var upperCutoff = losoreTlgBestandService.prosesserTotalbestand();
        return JsonNodeFactory.instance.objectNode()
                .put("upperCutoff", upperCutoff.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }

    @GetMapping("test/endringslogg/{lowerCutoff}")
    public JsonNode testEndringslogg(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime lowerCutoff) throws Exception {
        var sistEndretSisteInnslag = losoreTlgBestandService.prosesserEndringslogg(lowerCutoff);
        return JsonNodeFactory.instance.objectNode()
                .put("sistEndretSisteInnslag", sistEndretSisteInnslag.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
