package com.example.back.common.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Provider {
    KAKAO("kakao"), GOLDENTIME("goldentime");

    private final String value;
    private static final Map<String, Provider> PROVIDER_MAP =
            Arrays.stream(Provider.values())
                            .collect(Collectors.toMap(Provider::getValue, Function.identity()));

    Provider(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }
    @JsonCreator
    public static Provider getProviderFromValue(String value) {
        return Optional.ofNullable(PROVIDER_MAP.get(value)).orElseThrow(IllegalArgumentException::new);
    }

}
