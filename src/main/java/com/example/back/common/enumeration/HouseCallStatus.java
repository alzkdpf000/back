package com.example.back.common.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HouseCallStatus {
    COMPLETED("completed"),  // 완료
    PENDING("pending");      // 완료 X / 진행 중

    private final String value;
    private static final Map<String, HouseCallStatus> STATUS_MAP =
            Arrays.stream(HouseCallStatus.values()).collect(Collectors.toMap(HouseCallStatus::getValue, Function.identity()));
    HouseCallStatus(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return this.value;
    }
    @JsonCreator
    public static HouseCallStatus getStatusFromValue(String value) {
        return Optional.ofNullable(STATUS_MAP.get(value)).orElseThrow(IllegalArgumentException::new);
    }

}
