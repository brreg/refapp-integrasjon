package refappintegrasjon.dto.adresse;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AdresseType {
    VEGADRESSE("adressetype.vegadresse"),
    STEDSADRESSE("adressetype.stedsadresse"),
    POSTBOKSADRESSE("adressetype.postboksadresse"),
    USTRUKTURERTADRESSE("adressetype.ustrukturertAdresse"),
    INTERNASJONALADRESSE("adressetype.internasjonalAdresse");

    @JsonValue
    private final String value;

    AdresseType(String value) {
        this.value = value;
    }

    public static AdresseType fromValue(String v) {
        for (AdresseType c : AdresseType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String value() {
        return value;
    }
    }
