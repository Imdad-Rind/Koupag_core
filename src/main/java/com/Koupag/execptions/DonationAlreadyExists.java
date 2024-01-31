package com.Koupag.execptions;

import java.io.Serial;

public class DonationAlreadyExists extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public DonationAlreadyExists() {
    }

    public DonationAlreadyExists(String message) {
        super(message);
    }
}
