package refappintegrasjon.dto.losore.formuesgode;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaerskiltIdentifisert extends Formuesgode {
    private String identifikator;
    private String identifiseringstype;
    private String identifiseringstypeBeskrivelse;

    @Builder
    public SaerskiltIdentifisert(String type,
                                 String typeBeskrivelse,
                                 Brok eierandel,
                                 String identifikator,
                                 String identifiseringstype,
                                 String identifiseringstypeBeskrivelse,
                                 ZonedDateTime tidspunktForAbandonering) {
        super(Identifiseringsmate.SARSKILT_IDENTIFISERT, type, typeBeskrivelse, eierandel, tidspunktForAbandonering);
        this.identifikator = identifikator;
        this.identifiseringstype = identifiseringstype;
        this.identifiseringstypeBeskrivelse = identifiseringstypeBeskrivelse;
    }
}
