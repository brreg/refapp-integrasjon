package refappintegrasjon.dto.losore.rettsstiftelse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Krav {
    private List<BelopValuta> belop;
    private String kravSalgspant;
    private String kravSalgspantBeskrivelse;
    private String kravFordringer;
    private String kravFordringerBeskrivelse;
}
