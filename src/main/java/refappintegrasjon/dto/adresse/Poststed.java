package refappintegrasjon.dto.adresse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Poststed {
    private String navn;
    private String postnummer;
}
