package refappintegrasjon.dto.ektepakt;

import lombok.Builder;

@Builder
public record Personnavn(
        String fornavn,
        String mellomnavn,
        String etternavn) {
}
