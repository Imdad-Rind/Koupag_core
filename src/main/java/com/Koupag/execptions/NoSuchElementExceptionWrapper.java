package com.Koupag.execptions;

import java.io.Serial;
import java.util.NoSuchElementException;

public class NoSuchElementExceptionWrapper extends NoSuchElementException {
    @Serial
    private static final long serialVersionUID = 1L;
    public NoSuchElementExceptionWrapper() {
    }

    public NoSuchElementExceptionWrapper(String s, Throwable cause) {
        super(s, cause);
    }


}
