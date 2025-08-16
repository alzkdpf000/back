package com.example.back.common.exception;

public class NoticeNotFoundException extends RuntimeException {
    public NoticeNotFoundException() {;}
    public NoticeNotFoundException(String message) { super(message); }
}

