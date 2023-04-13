package refappintegrasjon.dto.adresse;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Stedsadresse extends NorskAdresse {
    private String stedsnavn;

    @Builder
    public Stedsadresse(String coNavn, Adressebrukskategori brukskategori, Poststed poststed, Kommune kommune, String stedsnavn) {
        super(AdresseType.STEDSADRESSE, coNavn, brukskategori, poststed, kommune);
        this.stedsnavn = stedsnavn;
    }
}
