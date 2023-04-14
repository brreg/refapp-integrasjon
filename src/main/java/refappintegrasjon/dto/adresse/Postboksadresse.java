package refappintegrasjon.dto.adresse;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Postboksadresse extends NorskAdresse {
    private String postboksnummer;
    private String postboksanleggsnavn;

    @Builder
    public Postboksadresse(String coNavn, Adressebrukskategori brukskategori, Poststed poststed,
                           Kommune kommune, String postboksnummer, String anleggsnavn) {
        super(AdresseType.POSTBOKSADRESSE, coNavn, brukskategori, poststed, kommune);
        this.postboksnummer = postboksnummer;
        this.postboksanleggsnavn = anleggsnavn;
    }
}
