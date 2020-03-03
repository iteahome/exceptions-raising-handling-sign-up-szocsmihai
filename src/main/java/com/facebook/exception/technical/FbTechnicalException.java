package com.facebook.exception.technical;

import com.facebook.exception.FacebookException;

public class FbTechnicalException extends FacebookException {

    public FbTechnicalException() {
    }

    public FbTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
