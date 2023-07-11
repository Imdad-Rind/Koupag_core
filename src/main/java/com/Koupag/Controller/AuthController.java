package com.Koupag.Controller;

import com.Koupag.DTO.LoginDTO;
import com.Koupag.DTO.LoginResponseDTO;
import com.Koupag.DTO.RegisterDTO;
import com.Koupag.Services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth/")
public class AuthController {
    private AuthenticationService authenticationService;

    public AuthController( AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
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
