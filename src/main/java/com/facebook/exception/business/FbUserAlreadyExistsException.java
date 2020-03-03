package com.facebook.exception.business;

public class FbUserAlreadyExistsException extends FbBusinessException {

    public FbUserAlreadyExistsException() {
    }

    public FbUserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
