package refappintegrasjon.dto.adresse;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Vegadresse extends NorskAdresse {
    private String vegadresseID;
    private String bruksenhetsnummer;
    private String adressenavn;
    private String kortAdressenavn;
    private Adressenummer nummer;
    private String adressenavnTillegg;

    @Builder
    public Vegadresse(String coNavn, Adressebrukskategori brukskategori, Poststed poststed,
                      Kommune kommune, String vegadresseID, String bruksenhetsnummer, String adressenavn,
                      String kortAdressenavn, Adressenummer nummer, String adressenavnTillegg) {
        super(AdresseType.VEGADRESSE, coNavn, brukskategori, poststed, kommune);
        this.vegadresseID = vegadresseID;
        this.bruksenhetsnummer = bruksenhetsnummer;
        this.adressenavn = adressenavn;
        this.kortAdressenavn = kortAdressenavn;
        this.nummer = nummer;
        this.adressenavnTillegg = adressenavnTillegg;
    }
}
