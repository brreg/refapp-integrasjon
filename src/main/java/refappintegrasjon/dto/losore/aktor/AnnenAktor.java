package refappintegrasjon.dto.losore.aktor;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import refappintegrasjon.dto.adresse.GeografiskAdresse;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnnenAktor extends Aktor {
    private String navn;

    @Builder
    public AnnenAktor(GeografiskAdresse geografiskAdresse, String navn) {
        super(AktorType.ANNEN_AKTOR, geografiskAdresse);
        this.navn = navn;
    }
}
