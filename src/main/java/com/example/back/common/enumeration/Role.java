package com.example.back.common.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

//enum ('doctor','member','admin') default 'member',

public enum Role {
    DOCTOR("doctor"), MEMBER("member"), ADMIN("admin");

    private final String value;

    private static final Map<String, Role> ROLE_MAP =
            Arrays.stream(Role.values())
                    .collect(Collectors.toMap(Role::getValue, Function.identity()));

    Role(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }
    @JsonCreator
    public static Role getRoleValue(String value) {
        return Optional.ofNullable(ROLE_MAP.get(value))
                .orElseThrow(IllegalArgumentException::new);

    }

}
