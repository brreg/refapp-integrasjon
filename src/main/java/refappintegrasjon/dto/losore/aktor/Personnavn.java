package refappintegrasjon.dto.losore.aktor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Personnavn {
    private String fornavn;
    private String mellomnavn;
    private String etternavn;
}
