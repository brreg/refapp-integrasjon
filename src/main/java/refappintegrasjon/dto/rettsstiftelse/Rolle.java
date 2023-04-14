package refappintegrasjon.dto.rettsstiftelse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import refappintegrasjon.dto.aktor.Aktor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rolle {
    private String rolletype;
    private String rolletypeBeskrivelse;
    private String rollegruppetype;
    private String rollegruppetypeBeskrivelse;
    private Aktor rolleinnehaver;
}
