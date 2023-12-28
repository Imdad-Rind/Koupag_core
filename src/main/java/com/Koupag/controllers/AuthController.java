package com.Koupag.controllers;

import com.Koupag.dtos.login.LoginDTO;
import com.Koupag.dtos.login.LoginResponseDTO;
import com.Koupag.dtos.verify.otpAndEmail;
import com.Koupag.models.User;
import com.Koupag.models.VerifiedUser;
import com.Koupag.services.*;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth/")
public class  AuthController {
    private final AuthenticationService authenticationService;
    private final EmailService emailService;
    private final OTPService otpService;
    private final VerifiedUserService verifiedUserService;
    public AuthController(AuthenticationService authenticationService, RolesService rolesService,
                          EmailService emailService, OTPService otpService, VerifiedUserService verifiedUserService) {
        this.authenticationService = authenticationService;
        this.emailService = emailService;
        this.otpService = otpService;
        this.verifiedUserService = verifiedUserService;
    }

    
    @PostMapping("request_register")
    public ResponseEntity<Void> requestRegister(@RequestBody VerifiedUser verifiedUser) throws Exception {
        String userEmail = verifiedUser.getEmail();
        if(!verifiedUserService.isUserVerified(verifiedUser.getEmail())){
            String otp = otpService.generateAndSendOtp(userEmail);
            //emailService.sendOTP(userEmail,otp);
            System.out.println(otp);
            verifiedUserService.NewVerifiedUser(verifiedUser);
        }else {
            throw new Exception("user already verified");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("register")
    public ResponseEntity<Optional<LoginResponseDTO>> register(@RequestBody User user) {
        try {
            if (verifiedUserService.isUserVerified(user.getEmail())){
                if(!authenticationService.checkUserRegistrationByEmail(user.getEmail())){
                    authenticationService.registerUser(user);
                    return login(new LoginDTO(user.getCNIC(),user.getPassword()));
                }else{
                    throw new Exception("user already registered");
                }

            }else {
                throw new Exception("user not verified");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(Optional.empty(),HttpStatus.UNAUTHORIZED);
        }
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
    public ResponseEntity<Void>verifyOTP(@RequestBody otpAndEmail otpAndEmail){
        User user;
        try {
           if(otpService.verifyOtp(otpAndEmail.getEmail(), otpAndEmail.getOtp())) {
              
               verifiedUserService.verifyUserByEmail(otpAndEmail.getEmail());
               
           }else {
               throw new Exception("otp not verified");
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
