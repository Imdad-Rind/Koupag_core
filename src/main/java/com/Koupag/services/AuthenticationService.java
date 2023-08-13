package com.Koupag.services;

import com.Koupag.dtos.LoginDTO;
import com.Koupag.dtos.LoginResponseDTO;
import com.Koupag.models.User;

import java.util.Optional;

public interface AuthenticationService {

    public User registerUser(User user) throws Exception;
    public Optional<LoginResponseDTO> loginUser(LoginDTO loginDTO);

}
