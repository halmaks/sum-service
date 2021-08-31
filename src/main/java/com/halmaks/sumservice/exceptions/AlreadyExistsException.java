package com.halmaks.sumservice.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(final String errorMessage) {
        super(errorMessage);
    }
}
