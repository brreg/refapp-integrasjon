package refappintegrasjon.dto.ektepakt;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
public record Oppslag(
        String rolleinnehaver,
        Integer antallEktepakt,
        ZonedDateTime oppslagstidspunkt,
        String spraak,
        List<Ektepakt> ektepakt) {
}
