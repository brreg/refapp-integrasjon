package refappintegrasjon.dto.ektepakt;

import lombok.Builder;
import refappintegrasjon.dto.adresse.GeografiskAdresse;

@Builder
public record Person(
        Personnavn navn,
        GeografiskAdresse adresse) {
}
