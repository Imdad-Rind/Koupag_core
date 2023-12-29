package com.Koupag.services;

import com.Koupag.dtos.login.LoginDTO;
import com.Koupag.dtos.login.LoginResponseDTO;
import com.Koupag.models.User;

import java.util.Optional;

public interface AuthenticationService {

    public User registerUser(User user) throws Exception;
    public Optional<LoginResponseDTO> loginUser(LoginDTO loginDTO);
    public boolean checkUserRegistrationByEmail(String email);
    
    
}
