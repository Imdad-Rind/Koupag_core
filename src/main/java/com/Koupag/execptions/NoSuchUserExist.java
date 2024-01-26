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


}
