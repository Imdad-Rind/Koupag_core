package com.Koupag.execptions;

import java.io.Serial;

public class DonorNotFound extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public DonorNotFound() {
    }

    public DonorNotFound(String message) {
        super(message);
    }
}
