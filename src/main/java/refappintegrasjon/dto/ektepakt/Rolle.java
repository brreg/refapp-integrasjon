package refappintegrasjon.dto.ektepakt;

import lombok.Builder;

import java.util.List;

@Builder
public record Rolle(
        String rolletype,
        String rolletypebeskrivelse,
        Person person,
        List<Avtaleinnhold> avtaleinnhold) {
}
