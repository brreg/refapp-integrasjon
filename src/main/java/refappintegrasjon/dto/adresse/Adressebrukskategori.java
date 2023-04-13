package refappintegrasjon.dto.adresse;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum Adressebrukskategori {
    FORRETNINGSADRESSE("forretningsadresse"),
    BELIGGENHETSADRESSE("beliggenhetsadresse"),
    BOSTEDSADRESSE("bostedsadresse"),
    OPPHOLDSADRESSE("oppholdsadresse"),
    POSTADRESSE("postadresse");

    @Getter
    @JsonValue
    private final String visningsnavn;

    Adressebrukskategori(String visningnavn) {
        this.visningsnavn = visningnavn;
    }
}
