package com.example.back.common.exception;

public class InquiryNotFoundException extends RuntimeException {
    public InquiryNotFoundException() {;}
    public InquiryNotFoundException(String message) { super(message); }
}

