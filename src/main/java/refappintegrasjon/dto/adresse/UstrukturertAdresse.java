package refappintegrasjon.dto.adresse;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UstrukturertAdresse extends NorskAdresse {
    private String adresselinje1;
    private String adresselinje2;
    private String adresselinje3;

    @Builder
    public UstrukturertAdresse(String coNavn, Adressebrukskategori brukskategori, Poststed poststed, Kommune kommune, String adresselinje1, String adresselinje2, String adresselinje3) {
        super(AdresseType.USTRUKTURERTADRESSE, coNavn, brukskategori, poststed, kommune);
        this.adresselinje1 = adresselinje1;
        this.adresselinje2 = adresselinje2;
        this.adresselinje3 = adresselinje3;
    }
}
