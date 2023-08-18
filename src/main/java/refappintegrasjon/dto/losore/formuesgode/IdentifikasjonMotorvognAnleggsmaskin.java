package refappintegrasjon.dto.losore.formuesgode;

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
public class IdentifikasjonMotorvognAnleggsmaskin {
    private String merke;
    private String aarsmodell;
    private String identifikasjonsnummer;
}
