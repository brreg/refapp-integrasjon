package refappintegrasjon.dto.losore.aktor;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import refappintegrasjon.dto.adresse.GeografiskAdresse;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Person extends Aktor {
    private Personnavn personnavn;
    private String fodselsnummerEllerDNummer;

    @Builder
    public Person(GeografiskAdresse geografiskAdresse, Personnavn personnavn, String fodselsnummerEllerDNummer) {
        super(AktorType.PERSON, geografiskAdresse);
        this.personnavn = personnavn;
        this.fodselsnummerEllerDNummer = fodselsnummerEllerDNummer;
    }
}
