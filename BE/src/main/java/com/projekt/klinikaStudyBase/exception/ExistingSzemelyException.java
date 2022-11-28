package com.projekt.klinikaStudyBase.exception;

public class ExistingSzemelyException extends RuntimeException {

	public ExistingSzemelyException(final String taj) {
		super(String.format("A megadott személy már létezik a rendszerben a következő taj számmal: %s", taj));
	}

	public int getErrorCode() {
		return ErrorCodes.FIZETESI_MOD_NOT_FOUND;
	}
}
