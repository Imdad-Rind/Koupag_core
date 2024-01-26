package com.Koupag.execptions;

import java.io.Serial;

public class UserAlreadyRegistered extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public UserAlreadyRegistered() {
    }

    public UserAlreadyRegistered(String message) {
        super(message);
    }


}
