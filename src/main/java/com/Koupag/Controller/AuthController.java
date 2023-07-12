package com.Koupag.Controller;

import com.Koupag.DTO.LoginDTO;
import com.Koupag.DTO.LoginResponseDTO;
import com.Koupag.DTO.RegisterDTO;
import com.Koupag.Model.Roles;
import com.Koupag.Services.AuthenticationService;
import com.Koupag.Services.RolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/auth/")
public class AuthController {
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
    public ResponseEntity<Optional<LoginResponseDTO>> register(@RequestBody RegisterDTO registerDTO){
        try {
            authenticationService.registerUser(registerDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(),HttpStatus.UNAUTHORIZED);
        }
        return login(new LoginDTO(registerDTO.getUsername(),registerDTO.getPassword()));
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
