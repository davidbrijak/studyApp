package com.projekt.klinikaStudyBase.exception;

public class VizsgalatiModNotFoundException extends RuntimeException {
    public VizsgalatiModNotFoundException(final String id) {
        super(String.format("A vizsgálati mód nem talalhato. Id: %s", id));
    }

    public int getErrorCode() {
        return ErrorCodes.VIZSGALATI_MOD_NOT_FOUND;
    }
}
