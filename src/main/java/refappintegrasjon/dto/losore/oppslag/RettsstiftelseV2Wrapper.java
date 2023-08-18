package refappintegrasjon.dto.losore.oppslag;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import refappintegrasjon.dto.ErrorDetails;
import refappintegrasjon.dto.losore.rettsstiftelse.Rettsstiftelse;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RettsstiftelseV2Wrapper {

    String sokeparameter;
    ZonedDateTime oppslagstidspunkt;
    Integer antallRettsstiftelser;
    List<Rettsstiftelse> rettsstiftelse;

    @JsonUnwrapped
    ErrorDetails errorDetails;
}
