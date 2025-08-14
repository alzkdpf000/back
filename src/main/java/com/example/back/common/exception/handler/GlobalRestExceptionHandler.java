package com.example.back.common.exception.handler;


import com.example.back.common.exception.InquiryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.back.controller")
public class GlobalRestExceptionHandler {
    @ExceptionHandler(InquiryNotFoundException.class)
    public ResponseEntity<String> handleException(InquiryNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
