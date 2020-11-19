package com.projekt.event_scheduling_application.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public class ESAException extends RuntimeException {
    private String fieldName;
    private int status;
    private String message;

    public Integer getStatus() {
        return Optional.ofNullable(status).orElse(HttpStatus.BAD_REQUEST.value());
    }

    public String getFieldName() {
        return fieldName;
    }

    public ESAException(String message) {
        super(message);
    }

    public ESAException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }
}
