package refappintegrasjon.dto.rettsstiftelse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import refappintegrasjon.dto.formuesgode.Formuesgode;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "dokumentnummer", "type", "typeBeskrivelse", "status", "statusBeskrivelse",
        "innkomsttidspunkt", "beslutningstidspunkt", "avgrensingRettsstiftelse", "utlopRettsvernstid",
        "slettet", "paategning", "rolle", "formuesgode", "prioritetsvikelse", "krav",
        "konkurs", "saerskiltPrioritet", "skifteutlegg", "vergemaal", "gjeldsordning"
})
public class Rettsstiftelse {
    private String dokumentnummer;
    private String type;
    private String typeBeskrivelse;
    private String status;
    private String statusBeskrivelse;
    private ZonedDateTime innkomsttidspunkt;
    private ZonedDateTime beslutningstidspunkt;
    private String avgrensingRettsstiftelse;
    // For endringslogg/totalbestand
    private LocalDate utlopRettsvernstid;
    private LocalDate slettet;
    private List<String> paategning;

    private List<Rolle> rolle;
    private List<Formuesgode> formuesgode;
    private List<Prioritetsvikelse> prioritetsvikelse;
    private Krav krav;
    private Konkurs konkurs;
    private SaerskiltPrioritet saerskiltPrioritet;
    private Skifteutlegg skifteutlegg;
    private Vergemaal vergemaal;
    private Gjeldsordning gjeldsordning;
}
