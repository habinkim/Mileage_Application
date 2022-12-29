package com.habin.marketboro_mileage_task.mileage_event.exception;

import com.habin.marketboro_mileage_task.common.exception.MileageException;

public class MileageEventInvalidException extends MileageException {

    private static final String MESSAGE = "잘못된 요청입니다.";

    public MileageEventInvalidException(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    @Override
    public int statusCode() {
        return 400;
    }
}
