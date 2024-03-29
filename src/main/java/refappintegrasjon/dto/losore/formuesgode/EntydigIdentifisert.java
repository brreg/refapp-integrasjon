package refappintegrasjon.dto.losore.formuesgode;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntydigIdentifisert extends Formuesgode {
    private String registreringsnummerMotorvogn;
    private List<String> historiskRegistreringsnummerMotorvogn;

    @Builder
    public EntydigIdentifisert(String type,
                               String typeBeskrivelse,
                               Brok eierandel,
                               String registreringsnummerMotorvogn,
                               List<String> historiskRegistreringsnummerMotorvogn,
                               ZonedDateTime tidspunktForAbandonering) {
        super(Identifiseringsmate.ENTYDIG_IDENTIFISERT, type, typeBeskrivelse, eierandel, tidspunktForAbandonering);
        this.registreringsnummerMotorvogn = registreringsnummerMotorvogn;
        this.historiskRegistreringsnummerMotorvogn = new ArrayList<>(historiskRegistreringsnummerMotorvogn);
    }
}
