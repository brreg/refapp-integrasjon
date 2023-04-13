package refappintegrasjon.dto.endringslogg;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class EndringsloggRequest {

    ZonedDateTime lowerCutoff;
    Object[] lastSortValues;
}
