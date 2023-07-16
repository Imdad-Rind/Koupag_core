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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {


    private UserService userService;
    private RolesService rolesService;
    private AuthenticationManager authenticationManager;
    private MyTokenService myTokenService;
    private UserMapper userMapper;

    @Autowired
    public AuthenticationServiceImpl(UserService userService, RolesService rolesService, AuthenticationManager authenticationManager,
                                     MyTokenService myTokenService, UserMapper userMapper) {
        this.userService = userService;
        this.rolesService = rolesService;
        this.authenticationManager = authenticationManager;
        this.myTokenService = myTokenService;
        this.userMapper = userMapper;
    }

    @Override
    public UserModel registerUser(RegisterDTO registerDTO) throws Exception {
            Set<Roles> roles = new HashSet<>();
            for(Roles role :registerDTO.getUserRole()){
                roles.add(rolesService.getRolesById(role.getId()).get());
            }
            final UserModel newUser = userMapper.DTOtoUser(registerDTO, roles);
            final UserModel user = userService.creteNewUser(newUser);
            if(user == null){
                throw new Exception("user already exists");
            }
            return user;
    }

    @Override
    public Optional<LoginResponseDTO> loginUser(LoginDTO loginDTO) {
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword())
            );

            String token = myTokenService.generateJwt(auth);

           /* //return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
            // return new LoginResponseDTO(userService.getUserByUserName(loginDTO.getUsername()).get(), token);*/
            return Optional.of(new LoginResponseDTO(userService.getUserByUserName(loginDTO.getUsername()).get(), token));

        } catch(AuthenticationException e){
            return Optional.empty();
        }
    }


}
