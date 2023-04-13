package refappintegrasjon.dto.adresse;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NorskAdresse extends GeografiskAdresse {
    private Poststed poststed;
    private Kommune kommune;

    public NorskAdresse(AdresseType adresseType, String coNavn, Adressebrukskategori brukskategori, Poststed poststed,
                        Kommune kommune) {
        super(adresseType, coNavn, brukskategori);
        this.poststed = poststed;
        this.kommune = kommune;
    }
}
