package refappintegrasjon.dto.adresse;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Internasjonaladresse extends GeografiskAdresse {
    private String adressenavn;
    private String adressenummer;
    private String bygning;
    private String etasjenummer;
    private String boenhet;
    private String postboks;
    private String postkode;
    private String byEllerStedsnavn;
    private String region;
    private String distriktEllerBydel;
    private String landkode;
    private String friAdressetekst;
    private String adresseidentifikator;

    @Builder
    public Internasjonaladresse(String coNavn, Adressebrukskategori brukskategori, String adressenavn,
                                String adressenummer, String bygning, String etasjenummer, String boenhet,
                                String postboks, String postkode, String byEllerStedsnavn, String region,
                                String distriktEllerBydel, String landkode, String friAdressetekst, String adresseidentifikator) {
        super(AdresseType.INTERNASJONALADRESSE, coNavn, brukskategori);
        this.adressenavn = adressenavn;
        this.adressenummer = adressenummer;
        this.bygning = bygning;
        this.etasjenummer = etasjenummer;
        this.boenhet = boenhet;
        this.postboks = postboks;
        this.postkode = postkode;
        this.byEllerStedsnavn = byEllerStedsnavn;
        this.region = region;
        this.distriktEllerBydel = distriktEllerBydel;
        this.landkode = landkode;
        this.friAdressetekst = friAdressetekst;
        this.adresseidentifikator = adresseidentifikator;
    }
}
