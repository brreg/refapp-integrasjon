package refappintegrasjon.dto.totalbestand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import refappintegrasjon.dto.rettsstiftelse.Rettsstiftelse;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TotalbestandResponse {

    ZonedDateTime upperCutoffForrigeRequest;
    Object[] sortValues;
    Integer antallRettsstiftelser;
    List<Rettsstiftelse> rettsstiftelse;
}
