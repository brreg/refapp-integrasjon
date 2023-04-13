package refappintegrasjon.dto.totalbestand;

import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@Builder
public class TotalbestandRequest {

    ZonedDateTime upperCutoff;
    Object[] lastSortValues;
}
