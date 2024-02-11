package com.Koupag.execptions;

public class WrongCredsException extends RuntimeException{
    public WrongCredsException(String message) {
        super(message);
    }
}
