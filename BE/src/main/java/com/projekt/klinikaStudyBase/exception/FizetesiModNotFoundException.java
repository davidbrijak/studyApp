package com.projekt.klinikaStudyBase.exception;

public class FizetesiModNotFoundException extends RuntimeException {
    public FizetesiModNotFoundException(final String id) {
        super(String.format("A fizetési mód nem talalhato. Id: %s", id));
    }

    public int getErrorCode() {
        return ErrorCodes.FIZETESI_MOD_NOT_FOUND;
    }
}
