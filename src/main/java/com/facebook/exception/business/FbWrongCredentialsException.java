package com.facebook.exception.business;

public class FbWrongCredentialsException extends FbBusinessException {

    public FbWrongCredentialsException() {
    }

    public FbWrongCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
