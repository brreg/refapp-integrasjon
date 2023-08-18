package refappintegrasjon.dto.losore.rettsstiftelse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vergemaal {
    private Boolean gjelderPersonligeForhold;
    private Boolean gjelderOkonomiskeForhold;
    private String varighet;
    private String varighetBeskrivelse;
    private LocalDate tidsbegrensetTilDato;
}
