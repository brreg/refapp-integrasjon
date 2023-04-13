package refappintegrasjon.dto.formuesgode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brok {
    private Integer teller;
    private Integer nevner;
}
