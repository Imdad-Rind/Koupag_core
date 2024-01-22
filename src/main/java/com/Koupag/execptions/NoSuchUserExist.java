package com.Koupag.execptions;

import java.io.Serial;

public class NoSuchUserExist extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    public NoSuchUserExist() {
    }

    public NoSuchUserExist(String message) {
        super(message);
    }

    public NoSuchUserExist(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchUserExist(Throwable cause) {
        super(cause);
    }
}
