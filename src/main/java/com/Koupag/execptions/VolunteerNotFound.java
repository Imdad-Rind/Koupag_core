package com.Koupag.execptions;

import java.io.Serial;

public class VolunteerNotFound extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public VolunteerNotFound() {
    }

    public VolunteerNotFound(String message) {
        super(message);
    }
}
