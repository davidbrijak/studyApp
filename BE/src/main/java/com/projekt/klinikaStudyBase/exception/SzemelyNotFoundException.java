package com.projekt.klinikaStudyBase.exception;

public class SzemelyNotFoundException extends RuntimeException{
    public SzemelyNotFoundException(final String id) {
        super(String.format("A szemely nem talalhato. Id: %s", id));
    }

    public int getErrorCode() {
        return ErrorCodes.SZEMELY_NOT_FOUND;
    }
}
