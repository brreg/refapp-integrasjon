package refappintegrasjon.dto.losore.aktor;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AktorType {
    PERSON("aktortype.person"),
    VIRKSOMHET("aktortype.virksomhet"),
    ANNEN_AKTOR("aktortype.annenaktor");

    @JsonValue
    private final String value;

    AktorType(String value) {
        this.value = value;
    }

    public static AktorType fromValue(String v) {
        for (AktorType c : AktorType.values()) {
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
