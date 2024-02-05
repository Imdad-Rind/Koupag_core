package com.Koupag.execptions;

import java.io.Serial;

public class OldPasswordDoNotMatch extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public OldPasswordDoNotMatch() {
    }

    public OldPasswordDoNotMatch(String message) {
        super(message);
    }
}
