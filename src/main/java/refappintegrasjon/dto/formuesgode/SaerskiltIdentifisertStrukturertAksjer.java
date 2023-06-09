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
public class SaerskiltIdentifisertStrukturertAksjer extends Formuesgode {
    private String organisasjonsnummer;
    private String antallAksjer;
    private String aksjeklasseBeskrivelse;
    private String aksjeklasseidentifikator;

    @Builder
    public SaerskiltIdentifisertStrukturertAksjer(String type,
                                                  String typeBeskrivelse,
                                                  Brok eierandel,
                                                  String organisasjonsnummer,
                                                  String antallAksjer,
                                                  String aksjeklasseBeskrivelse,
                                                  String aksjeklasseidentifikator,
                                                  ZonedDateTime tidspunktForAbandonering) {
        super(Identifiseringsmate.SARSKILT_IDENTIFISERT_STRUKTURERT_AKSJER, type, typeBeskrivelse, eierandel, tidspunktForAbandonering);
        this.organisasjonsnummer = organisasjonsnummer;
        this.antallAksjer = antallAksjer;
        this.aksjeklasseBeskrivelse = aksjeklasseBeskrivelse;
        this.aksjeklasseidentifikator = aksjeklasseidentifikator;
    }
}
