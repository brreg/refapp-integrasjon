package refappintegrasjon.dto.losore.rettsstiftelse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Gjeldsordning {
    private String type;
    private String typeBeskrivelse;
    private Tidsperiode periode;
    private String meldefristKrav;
    private String kravMeldesTil;
    private Integer gjeldsordningsperiodeAntallMaaneder;
    private Integer gjeldsordningsperiodeAntallAar;
    private String namsmannensSaksnummer;
}
