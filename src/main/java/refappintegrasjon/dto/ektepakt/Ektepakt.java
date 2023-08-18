package refappintegrasjon.dto.ektepakt;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
public record Ektepakt(
        Long ektepaktnummer,
        ZonedDateTime innkomsttidspunkt,
        String ektepakttype,
        String ektepakttypebeskrivelse,
        String status,
        String statusbeskrivelse,
        String opprettelse,
        String opprettelsestypebeskrivelse,
        Boolean harSupplerendeOpplysningerIDokument,
        List<String> paategning,
        List<Avtaleinnhold> avtaleinnhold, // Avtaleinnhold knyttet til begge roller
        List<Rolle> rolle) {
}
