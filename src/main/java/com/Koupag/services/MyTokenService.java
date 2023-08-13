package com.Koupag.services;

import org.springframework.security.core.Authentication;

public interface MyTokenService {
    public String generateJwt(Authentication auth);
}
