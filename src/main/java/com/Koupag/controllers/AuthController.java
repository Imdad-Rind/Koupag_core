package com.Koupag.controllers;

import com.Koupag.dtos.login.LoginDTO;
import com.Koupag.dtos.login.LoginResponseDTO;
import com.Koupag.dtos.register.RegisterRequest;
import com.Koupag.mappers.UserMapper;
import com.Koupag.models.Roles;
import com.Koupag.models.User;
import com.Koupag.services.AuthenticationService;
import com.Koupag.services.RolesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/auth/")
public class  AuthController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final RolesService rolesService;

    public AuthController(AuthenticationService authenticationService, UserMapper userMapper, RolesService rolesService) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
        this.rolesService = rolesService;
    }

    @GetMapping("/userTypes")
    public ResponseEntity<List<Roles>> roles(){
        return new ResponseEntity<>(rolesService.getAllRoles(),HttpStatus.OK);
    }
    @PostMapping("register")
    public ResponseEntity<Optional<LoginResponseDTO>> register(@RequestBody User user)/* throws JsonProcessingException*/ {
        
       /* user.getAddress().setUser(user);
        user.setAddress(user.getAddress());
        System.out.println(user.getAddress().toString());*/
     
        try {
            authenticationService.registerUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(Optional.empty(),HttpStatus.UNAUTHORIZED);
        }
        
        
        return login(new LoginDTO(user.getUsername(),user.getPassword()));
    }

    @PostMapping("login")
    public ResponseEntity<Optional<LoginResponseDTO>> login(@RequestBody LoginDTO loginDto){
        if (authenticationService.loginUser(loginDto).isPresent())
        {
            return new ResponseEntity<>(Optional.of(authenticationService.loginUser(loginDto).get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
    }
}
