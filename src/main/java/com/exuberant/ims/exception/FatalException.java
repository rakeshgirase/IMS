package com.exuberant.ims.exception;

public class FatalException extends RuntimeException {
    public FatalException(String format) {
        super(format);
    }
}
