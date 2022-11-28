package com.projekt.klinikaStudyBase.exception;

public class MeresNotFoundException extends RuntimeException {
    public MeresNotFoundException(final String id) {
        super(String.format("A mérés nem talalhato. Id: %s", id));
    }

    public int getErrorCode() {
        return ErrorCodes.MERES_MOD_NOT_FOUND;
    }
}
