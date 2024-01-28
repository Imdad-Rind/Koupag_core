package com.Koupag.execptions;

import java.io.Serial;

public class UnknownError extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    public UnknownError() {
    }

    public UnknownError(String message) {
        super(message);
    }

    public UnknownError(String message, Throwable cause) {
        super(message, cause);
    }


    public UnknownError(String s, Throwable cause, StackTraceElement[] stackTrace) {
    }
}
