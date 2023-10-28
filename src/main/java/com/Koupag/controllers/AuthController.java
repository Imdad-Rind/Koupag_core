package com.Koupag.controllers;

import com.Koupag.dtos.login.LoginDTO;
import com.Koupag.dtos.login.LoginResponseDTO;
import com.Koupag.dtos.verify.otpAndEmail;
import com.Koupag.models.Roles;
import com.Koupag.models.User;
import com.Koupag.services.*;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/auth/")
public class  AuthController {
    private final AuthenticationService authenticationService;
    private final RolesService rolesService;
    private final EmailService emailService;
    private final OTPService otpService;
    private final UserService userService;
    public AuthController(AuthenticationService authenticationService, RolesService rolesService,
                          EmailService emailService, OTPService otpService, UserService userService) {
        this.authenticationService = authenticationService;
        this.rolesService = rolesService;
        this.emailService = emailService;
        this.otpService = otpService;
        this.userService = userService;
    }

    @GetMapping("/userTypes")
    public ResponseEntity<List<Roles>> roles(){
        return new ResponseEntity<>(rolesService.getAllRoles(),HttpStatus.OK);
    }
    @PostMapping("request_register")
    public ResponseEntity<String> requestRegister(@RequestBody User user) throws MessagingException {
        String userEmail = user.getEmail();
        String otp = otpService.generateAndSendOtp(userEmail);
        
        //emailService.sendOTP(userEmail,otp);
        System.out.println(otp);
        userService.cacheNewUser(userEmail,user);
        return new ResponseEntity<>("OTP sent succes \n verfiy email", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<Optional<LoginResponseDTO>> login(@RequestBody LoginDTO loginDto){
        if (authenticationService.loginUser(loginDto).isPresent())
        {
            return new ResponseEntity<>(Optional.of(authenticationService.loginUser(loginDto).get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
    }
    
    @PostMapping("verify-otp")
    public ResponseEntity<Optional<LoginResponseDTO>>verifyOTP(@RequestBody otpAndEmail otpAndEmail){
        User user = new User();
        try {
           if(otpService.verifyOtp(otpAndEmail.getEmail(), otpAndEmail.getOtp())) {
               user = userService.getCachedUser(otpAndEmail.getEmail());
               authenticationService.registerUser(user);
           }else {
               throw new Exception("otp not verfied");
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(Optional.empty(),HttpStatus.UNAUTHORIZED);
        }
        
        
        return login(new LoginDTO(user.getUsername(),user.getPassword()));
    }
}
