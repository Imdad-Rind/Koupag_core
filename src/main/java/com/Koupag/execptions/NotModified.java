package com.Koupag.execptions;

import java.io.Serial;

public class NotModified extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public NotModified() {
    }

    public NotModified(String message) {
        super(message);
    }

    public NotModified(String message, Throwable cause) {
        super(message, cause);
    }

    public NotModified(Throwable cause) {
        super(cause);
    }
}
