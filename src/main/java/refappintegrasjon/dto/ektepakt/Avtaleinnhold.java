package refappintegrasjon.dto.ektepakt;

import lombok.Builder;

@Builder
public record Avtaleinnhold(
        String avtaletype,
        String avtaletypebeskrivelse) {
}
