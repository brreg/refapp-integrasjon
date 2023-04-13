package refappintegrasjon.dto.adresse;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true, property = "adresseType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Vegadresse.class, name = "adressetype.vegadresse"),
        @JsonSubTypes.Type(value = Postboksadresse.class, name = "adressetype.postboksadresse"),
        @JsonSubTypes.Type(value = Stedsadresse.class, name = "adressetype.stedsadresse"),
        @JsonSubTypes.Type(value = UstrukturertAdresse.class, name = "adressetype.ustrukturertAdresse"),
        @JsonSubTypes.Type(value = Internasjonaladresse.class, name = "adressetype.internasjonalAdresse")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "adresseType", "brukskategori", "coNavn",
        "adressenavnTillegg", "adressenavn", "kortAdressenavn", "nummer", "bruksenhetsnummer", "vegadresseID",
        "stedsnavn", "postboksnummer", "postboksanleggsnavn",
        "adresselinje1", "adresselinje2", "adresselinje3",
        "poststed", "kommune"
})
public class GeografiskAdresse {
    private AdresseType adresseType;
    private String coNavn;
    private Adressebrukskategori brukskategori;
}
