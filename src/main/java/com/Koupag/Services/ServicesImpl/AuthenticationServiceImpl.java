package com.Koupag.Services.ServicesImpl;

import com.Koupag.DTO.LoginDTO;
import com.Koupag.DTO.LoginResponseDTO;
import com.Koupag.DTO.RegisterDTO;
import com.Koupag.Model.Roles;
import com.Koupag.Model.UserModel;
import com.Koupag.Mappers.*;
import com.Koupag.Services.AuthenticationService;
import com.Koupag.Services.RolesService;
import com.Koupag.Services.MyTokenService;
import com.Koupag.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {


    private UserService userService;
    private RolesService rolesService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private MyTokenService myTokenService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService, RolesService rolesService,
                                     PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                                     MyTokenService myTokenService) {
        this.userService = userService;
        this.rolesService = rolesService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.myTokenService = myTokenService;
    }

    @Override
    public UserModel registerUser(RegisterDTO registerDTO) {
        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword());
        Roles userRole = rolesService.getByName("USER").get();

        Set<Roles> authorities = new HashSet<>();

        authorities.add(userRole);

        var newUser = UserMapper.mayToDTO(new UserModel(registerDTO.getUsername(), encodedPassword, authorities));

        return userService.creteNewUser(newUser);
    }

    @Override
    public LoginResponseDTO loginUser(LoginDTO loginDTO) {
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword())
            );

            String token = myTokenService.generateJwt(auth);

            //return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
            return new LoginResponseDTO(userService.getUserByUserName(loginDTO.getUsername()).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }


}
