package com.facebook.exception.business;

import com.facebook.exception.FacebookException;

public class FbBusinessException extends FacebookException {

    public FbBusinessException() {
    }

    public FbBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
