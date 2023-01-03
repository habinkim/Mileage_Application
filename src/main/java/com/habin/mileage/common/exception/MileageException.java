package com.habin.mileage.common.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class MileageException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    protected MileageException(String message) {
        super(message);
    }

    protected MileageException(String message, Throwable cause) {
        super(message, cause);
    }

    protected abstract int statusCode();

    protected void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }

}
