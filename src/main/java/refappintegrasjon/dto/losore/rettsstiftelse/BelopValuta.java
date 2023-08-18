package refappintegrasjon.dto.losore.rettsstiftelse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BelopValuta {
    private BigDecimal belop;
    private String valuta;
}
