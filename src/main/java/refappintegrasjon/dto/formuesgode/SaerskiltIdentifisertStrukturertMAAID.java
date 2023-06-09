package refappintegrasjon.dto.formuesgode;

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
public class SaerskiltIdentifisertStrukturertMAAID extends Formuesgode {
    private IdentifikasjonMotorvognAnleggsmaskin uregistrertMotorvogn;
    private String beskrivelse;

    @Builder
    public SaerskiltIdentifisertStrukturertMAAID(String type,
                                                 String typeBeskrivelse,
                                                 Brok eierandel,
                                                 IdentifikasjonMotorvognAnleggsmaskin uregistrertMotorvogn,
                                                 String beskrivelse,
                                                 ZonedDateTime tidspunktForAbandonering) {
        super(Identifiseringsmate.SARSKILT_IDENTIFISERT_STRUKTURERT_MAAID, type, typeBeskrivelse, eierandel, tidspunktForAbandonering);
        this.uregistrertMotorvogn = uregistrertMotorvogn;
        this.beskrivelse = beskrivelse;
    }
}
