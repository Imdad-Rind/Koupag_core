package com.Koupag.execptions;

import java.io.Serial;

public class RecipientNotFound extends  RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public RecipientNotFound() {
    }

    public RecipientNotFound(String message) {
        super(message);
    }
}
