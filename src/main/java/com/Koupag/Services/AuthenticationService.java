package com.Koupag.Services;

import com.Koupag.DTO.LoginDTO;
import com.Koupag.DTO.LoginResponseDTO;
import com.Koupag.DTO.RegisterDTO;
import com.Koupag.Model.UserModel;

import java.util.Optional;

public interface AuthenticationService {

    public UserModel registerUser(RegisterDTO registerDTO);
    public Optional<LoginResponseDTO> loginUser(LoginDTO loginDTO);

}
