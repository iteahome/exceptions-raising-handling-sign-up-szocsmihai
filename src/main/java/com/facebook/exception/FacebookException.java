package com.facebook.exception;

public class FacebookException extends Exception {

    public FacebookException() {
    }

    public FacebookException(String message, Throwable cause) {
        super(message, cause);
    }
}
