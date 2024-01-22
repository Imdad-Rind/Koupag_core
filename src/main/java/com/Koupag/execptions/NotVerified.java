package com.Koupag.execptions;

import java.io.Serial;

public class NotVerified extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public NotVerified() {
    }

    public NotVerified(String message) {
        super(message);
    }

    public NotVerified(String message, Throwable cause) {
        super(message, cause);
    }

    public NotVerified(Throwable cause) {
        super(cause);
    }
}
