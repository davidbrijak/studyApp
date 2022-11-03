package com.projekt.klinikaStudyBase.exception;

import org.springframework.http.HttpStatus;

public class KlinkaStutyBaseException extends RuntimeException {

        private static final long serialVersionUID = -6460780217986373940L;

        private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        public KlinkaStutyBaseException(String message, HttpStatus httpStatus) {
            super(message);
            this.httpStatus = httpStatus;
        }

        public KlinkaStutyBaseException(String message) {
            super(message);
        }
}
