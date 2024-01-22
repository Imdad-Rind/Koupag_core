package com.Koupag.execptions;

import java.io.Serial;

public class NullPointerExceptionWrapper extends NullPointerException{
    @Serial
    private static final long serialVersionUID = 1L;
    public NullPointerExceptionWrapper() {
    }

    public NullPointerExceptionWrapper(String s) {
        super(s);
    }
}
