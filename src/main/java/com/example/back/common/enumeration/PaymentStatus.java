package com.example.back.common.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PaymentStatus {

PENDING("pending"), SUCCESS("success"),CANCEL("cancel");

    private final String value;
    private static final Map<String, PaymentStatus> PAYMENTSTATUS_MAP =
            Arrays.stream(PaymentStatus.values()).collect(Collectors.toMap(PaymentStatus::getValue, Function.identity()));
    PaymentStatus(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return this.value;
    }
    @JsonCreator
    public static PaymentStatus getStatusFromValue(String value) {
        return Optional.ofNullable(PAYMENTSTATUS_MAP.get(value)).orElseThrow(IllegalArgumentException::new);
    }
}
