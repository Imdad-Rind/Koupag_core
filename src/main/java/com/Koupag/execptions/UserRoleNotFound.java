package com.Koupag.execptions;

import java.io.Serial;

public class UserRoleNotFound extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public UserRoleNotFound() {
    }

    public UserRoleNotFound(String message) {
        super(message);
    }
}
