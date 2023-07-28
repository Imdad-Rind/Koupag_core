package com.Koupag.Services.ServicesImpl;

import com.Koupag.DTO.LoginDTO;
import com.Koupag.DTO.LoginResponseDTO;
import com.Koupag.Model.Roles;
import com.Koupag.Model.User;
import com.Koupag.Mappers.*;
import com.Koupag.Services.*;
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


    private final UserService userService;
    private final RolesService rolesService;
    private final AuthenticationManager authenticationManager;
    private final MyTokenService myTokenService;
    private final UserMapper userMapper;
    private final UserTypeService userTypeService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService, RolesService rolesService, AuthenticationManager authenticationManager,
                                     MyTokenService myTokenService, UserMapper userMapper, UserTypeService userTypeService) {
        this.userService = userService;
        this.rolesService = rolesService;
        this.authenticationManager = authenticationManager;
        this.myTokenService = myTokenService;
        this.userMapper = userMapper;
        this.userTypeService = userTypeService;
    }

    @Override
    public User registerUser(User user) throws Exception {
            Set<Roles> roles = new HashSet<>();
            final var newUserRole = rolesService.getByName(user.getUserType()).get();
            roles.add(newUserRole);
            String userTypeRole = user.getUserType();
            var createdUser = userTypeService.creteNewTypeUser(user,roles,userTypeRole);

            if(createdUser == null){
                final User newUseruser = userService.creteNewUser(user);
                if(user == null){
                    throw new Exception("user already exists");
                }
                return user;
            }
        return createdUser;
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
