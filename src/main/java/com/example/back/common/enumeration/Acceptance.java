package com.example.back.common.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Acceptance {
    ACCEPTED("accepted"), UNACCEPTED("unaccepted");

    private final String value;
    private static final Map<String, Acceptance> ACCEPTANCE_MAP =
            Arrays.stream(Acceptance.values()).collect(Collectors.toMap(Acceptance::getValue, Function.identity()));
    Acceptance(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
    public static Acceptance getStatusFromValue(String value) {
        return Optional.ofNullable(ACCEPTANCE_MAP.get(value)).orElseThrow(IllegalArgumentException::new);
    }
}
