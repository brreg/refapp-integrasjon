package refappintegrasjon.dto.adresse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Kommune {
    private String kommunenummer;
    private String kommunenavn;
}
