package com.projekt.klinikaStudyBase.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String id) {
        super(String.format("A felhasznalo nem talalhato. Id: %s", id));
    }

    public int getErrorCode() {
        return ErrorCodes.USER_NOT_FOUND;
    }
}
