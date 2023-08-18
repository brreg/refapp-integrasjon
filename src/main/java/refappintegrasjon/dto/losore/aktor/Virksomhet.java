package refappintegrasjon.dto.losore.aktor;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import refappintegrasjon.dto.adresse.GeografiskAdresse;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Virksomhet extends Aktor {
    private String navn;
    private String organisasjonsnummer;

    @Builder
    public Virksomhet(GeografiskAdresse geografiskAdresse, String navn, String organisasjonsnummer) {
        super(AktorType.VIRKSOMHET, geografiskAdresse);
        this.navn = navn;
        this.organisasjonsnummer = organisasjonsnummer;
    }
}
