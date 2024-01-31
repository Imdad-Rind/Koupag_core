package com.Koupag.execptions;

import java.io.Serial;

public class DonationRequestNotFound extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public DonationRequestNotFound() {
    }

    public DonationRequestNotFound(String message) {
        super(message);
    }
}
