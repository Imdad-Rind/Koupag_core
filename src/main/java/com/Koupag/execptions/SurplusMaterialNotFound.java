package com.Koupag.execptions;

import java.io.Serial;

public class SurplusMaterialNotFound extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public SurplusMaterialNotFound() {
    }

    public SurplusMaterialNotFound(String message) {
        super(message);
    }
}
