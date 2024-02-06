package com.Koupag.controllers;

import com.Koupag.dtos.login.LoginDTO;
import com.Koupag.dtos.login.LoginResponseDTO;
import com.Koupag.dtos.verify.otpAndEmail;
import com.Koupag.execptions.AlreadyVerified;
import com.Koupag.execptions.NotVerified;
import com.Koupag.execptions.UnknownError;
import com.Koupag.execptions.UserAlreadyRegistered;
import com.Koupag.models.User;
import com.Koupag.models.UserSessionModel;
import com.Koupag.models.VerifiedUser;
import com.Koupag.services.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/auth/",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class  AuthController {
    private final AuthenticationService authenticationService;
    private final EmailService emailService;
    private final OTPService otpService;
    private final VerifiedUserService verifiedUserService;
    private final UserSessionService userSessionService;
    public AuthController(AuthenticationService authenticationService, RolesService rolesService,
                          EmailService emailService, OTPService otpService, VerifiedUserService verifiedUserService, UserSessionService userSessionService) {
        this.authenticationService = authenticationService;
        this.emailService = emailService;
        this.otpService = otpService;
        this.verifiedUserService = verifiedUserService;
        this.userSessionService = userSessionService;
    }

    
    @PostMapping("request_register")
    public ResponseEntity<Void> requestRegister(@RequestBody VerifiedUser verifiedUser) {
        String userEmail = verifiedUser.getEmail();
        if(!verifiedUserService.isUserVerified(verifiedUser.getEmail())){
            String otp = otpService.generateAndSendOtp(userEmail);
            //emailService.sendOTP(userEmail,otp);
            System.out.println(otp);
            verifiedUserService.NewVerifiedUser(verifiedUser);
        }else {
            throw new AlreadyVerified("User Already Verified");
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
                    throw new UserAlreadyRegistered("User already is Registered");
                }

            }else {
                throw new NotVerified(" User Not Verified");
            }
        } catch (Exception e) {
            throw new UnknownError("Unknown Error : " + e.getMessage(),e.getCause(),e.getStackTrace());
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

               otpService.verifyOtp(otpAndEmail.getEmail(), otpAndEmail.getOtp());
               verifiedUserService.verifyUserByEmail(otpAndEmail.getEmail());
               otpService.ExpireOTP(otpAndEmail.getOtp());
           }else {
               throw new AlreadyVerified("OTP is already Used");
           }
        } catch (Exception e) {
            throw new UnknownError("Unknown Error : " + e.getMessage(),e.getCause(),e.getStackTrace());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/logout/{id}")
    public ResponseEntity<Void>LogOut(@Autowired HttpServletRequest httpServletRequest,@Autowired HttpServletResponse httpServletResponse,@PathVariable(name = "id") UUID id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
            userSessionService.turnOffNotification(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}

