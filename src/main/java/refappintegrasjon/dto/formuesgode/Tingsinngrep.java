package refappintegrasjon.dto.formuesgode;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tingsinngrep extends Formuesgode {
    private String avgrensingTingsinnbegrep;
    private String avgrensingTingsinnbegrepBeskrivelse;
    private String beskrivelseAvgrensing;
    private String avtaletypeFordring;
    private String avtaletypeFordringBeskrivelse;

    @Builder
    public Tingsinngrep(String type, String typeBeskrivelse, Brok eierandel, String avgrensingTingsinnbegrep, String avgrensingTingsinnbegrepBeskrivelse, String beskrivelseAvgrensing, String avtaletypeFordring, String avtaletypeFordringBeskrivelse) {
        super(Identifiseringsmate.TINGSINNBEGREP, type, typeBeskrivelse, eierandel);
        this.avgrensingTingsinnbegrep = avgrensingTingsinnbegrep;
        this.avgrensingTingsinnbegrepBeskrivelse = avgrensingTingsinnbegrepBeskrivelse;
        this.beskrivelseAvgrensing = beskrivelseAvgrensing;
        this.avtaletypeFordring = avtaletypeFordring;
        this.avtaletypeFordringBeskrivelse = avtaletypeFordringBeskrivelse;
    }
}
