package com.example.back.common.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Result {
    DONE("done"), CANCEL("cancel");

    private final String value;
    private static final Map<String, Result> Result_MAP =
            Arrays.stream(Result.values())
                            .collect(Collectors.toMap(Result::getValue, Function.identity()));

    Result(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
    @JsonCreator
    public static Result getResultFromValue(String value) {
        return Optional.ofNullable(Result_MAP.get(value)).orElseThrow(IllegalArgumentException::new);
    }

}
