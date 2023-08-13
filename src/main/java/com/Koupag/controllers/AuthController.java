package com.Koupag.controllers;

import com.Koupag.dtos.LoginDTO;
import com.Koupag.dtos.LoginResponseDTO;
import com.Koupag.models.Roles;
import com.Koupag.models.User;
import com.Koupag.services.AuthenticationService;
import com.Koupag.services.RolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/auth/")
public class  AuthController {
    private AuthenticationService authenticationService;
    RolesService rolesService;

    public AuthController( AuthenticationService authenticationService,RolesService rolesService) {
        this.authenticationService = authenticationService;
        this.rolesService = rolesService;
    }

    @GetMapping("/userTypes")
    public ResponseEntity<List<Roles>> roles(){
        return new ResponseEntity<>(rolesService.getAllRoles(),HttpStatus.OK);
    }
    @PostMapping("register")
    public ResponseEntity<Optional<LoginResponseDTO>> register(@RequestBody User user){
        try {
            authenticationService.registerUser(user);
        } catch (Exception e) {
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
