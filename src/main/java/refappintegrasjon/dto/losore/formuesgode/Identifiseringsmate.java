package refappintegrasjon.dto.losore.formuesgode;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Identifiseringsmate {

    ENTYDIG_IDENTIFISERT("identifiseringsmate.entydig"),
    SARSKILT_IDENTIFISERT("identifiseringsmate.sarskilt"),
    SARSKILT_IDENTIFISERT_STRUKTURERT_MAAID("identifiseringsmate.sarskilt.strukturert.maaid"),
    SARSKILT_IDENTIFISERT_STRUKTURERT_AKSJER("identifiseringsmate.sarskilt.strukturert.aksjer"),
    TINGSINNBEGREP("identifiseringsmate.tingsinnbegrep");

    @JsonValue
    private final String value;

    Identifiseringsmate(String v) {
        value = v;
    }

    public static Identifiseringsmate fromValue(String v) {
        for (Identifiseringsmate c : Identifiseringsmate.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String value() {
        return value;
    }

}
