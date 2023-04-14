package refappintegrasjon.dto.formuesgode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true, property = "identifiseringsmate")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EntydigIdentifisert.class, name = "identifiseringsmate.entydig"),
        @JsonSubTypes.Type(value = SaerskiltIdentifisert.class, name = "identifiseringsmate.sarskilt"),
        @JsonSubTypes.Type(value = SaerskiltIdentifisertStrukturertMAAID.class, name = "identifiseringsmate.sarskilt.strukturert.maaid"),
        @JsonSubTypes.Type(value = SaerskiltIdentifisertStrukturertAksjer.class, name = "identifiseringsmate.sarskilt.strukturert.aksjer"),
        @JsonSubTypes.Type(value = Tingsinngrep.class, name = "identifiseringsmate.tingsinnbegrep")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Formuesgode {
    private Identifiseringsmate identifiseringsmate;
    private String type;
    private String typeBeskrivelse;
    private Brok eierandel;
}
