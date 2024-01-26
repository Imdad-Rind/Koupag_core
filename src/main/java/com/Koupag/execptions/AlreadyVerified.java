package com.Koupag.execptions;

import java.io.Serial;

public class AlreadyVerified extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public AlreadyVerified() {
    }

    public AlreadyVerified(String message) {
        super(message);
    }

}
