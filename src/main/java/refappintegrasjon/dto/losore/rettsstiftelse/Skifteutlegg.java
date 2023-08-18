package refappintegrasjon.dto.losore.rettsstiftelse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skifteutlegg {
    private String type;
    private String typeBeskrivelse;
}
