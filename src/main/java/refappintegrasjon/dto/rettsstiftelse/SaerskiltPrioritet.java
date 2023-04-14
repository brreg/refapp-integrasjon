package refappintegrasjon.dto.rettsstiftelse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaerskiltPrioritet {
    private String type;
    private String typeBeskrivelse;
}
