package refappintegrasjon.dto.losore.rettsstiftelse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Konkurs {
    private Boolean erTvangsavviklingEllerTvangsopplosning;
}
