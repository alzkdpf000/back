package com.example.back.common.exception;

public class PaymentUpdateFailException extends RuntimeException {
    public PaymentUpdateFailException() {;}
    public PaymentUpdateFailException(String message) { super(message); }
}

