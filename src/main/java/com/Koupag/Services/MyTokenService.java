package com.Koupag.Services;

import org.springframework.security.core.Authentication;

public interface MyTokenService {
    public String generateJwt(Authentication auth);
}
