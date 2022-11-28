package com.projekt.klinikaStudyBase.exception;

public class VizsgalatiLapNotFoundException extends RuntimeException {
    public VizsgalatiLapNotFoundException(final String id) {
        super(String.format("A vizsgálati lap nem talalhato. Id: %s", id));
    }

    public int getErrorCode() {
        return ErrorCodes.VIZSGALATI_MOD_NOT_FOUND;
    }
}
