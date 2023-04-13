package refappintegrasjon.dto.aktor;

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
    private GeografiskAdresse postadresse;

    @Builder
    public AnnenAktor(GeografiskAdresse geografiskAdresse, String navn) {
        super(AktorType.ANNEN_AKTOR, geografiskAdresse);
        this.navn = navn;
        //NOTE Denne skal være med i endringslogg/totalbestand, IKKE oppslag
        this.postadresse = geografiskAdresse;
    }
}
