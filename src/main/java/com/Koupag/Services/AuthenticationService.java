package com.Koupag.Services;

import com.Koupag.DTO.LoginDTO;
import com.Koupag.DTO.LoginResponseDTO;
import com.Koupag.Model.User;

import java.util.Optional;

public interface AuthenticationService {

    public User registerUser(User user) throws Exception;
    public Optional<LoginResponseDTO> loginUser(LoginDTO loginDTO);

}
