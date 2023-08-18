package refappintegrasjon.dto.losore.aktor;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import refappintegrasjon.dto.adresse.GeografiskAdresse;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true, property = "aktorType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Person.class, name = "aktortype.person"),
        @JsonSubTypes.Type(value = Virksomhet.class, name = "aktortype.virksomhet"),
        @JsonSubTypes.Type(value = AnnenAktor.class, name = "aktortype.annenaktor")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "aktorType", "personnavn", "fodselsnummerEllerDNummer", "navn",
        "organisasjonsnummer", "adresse", "postadresse"
})
public class Aktor {
    private AktorType aktorType;
    //NOTE Denne skal være med i oppslag, IKKE endringslogg/totalbestand
    private GeografiskAdresse adresse;
}
