package com.example.back.common.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Accpetance {
    ACCEPTED("accepted"), UNACCEPTED("unaccepted");

    private final String value;
    private static final Map<String, Accpetance> ACCEPTANCE_MAP =
            Arrays.stream(Accpetance.values()).collect(Collectors.toMap(Accpetance::getValue, Function.identity()));
    Accpetance(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
    public static Accpetance getStatusFromValue(String value) {
        return Optional.ofNullable(ACCEPTANCE_MAP.get(value)).orElseThrow(IllegalArgumentException::new);
    }
}
