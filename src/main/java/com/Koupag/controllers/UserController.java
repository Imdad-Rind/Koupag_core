package com.Koupag.controllers;

import com.Koupag.models.User;
import com.Koupag.models.UserSessionModel;
import com.Koupag.services.UserService;
import com.Koupag.services.UserSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("api/user/")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService, UserSessionService userSessionService) {
        this.userService = userService;
        this.userSessionService = userSessionService;
    }

    private final UserSessionService userSessionService;

    @PostMapping(value = "session-register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<UserSessionModel>> registerSession(@RequestBody UserSessionModel userSessionModel){
        // check if user is registered
        try{
            Optional<User> user = userService.getUserByCNIC(userSessionModel.getCard());
            if (user.isPresent() && user.get().getName() != null) {
                UserSessionModel userSessionModel1 = userSessionService.registerForNotification(userSessionModel);
                return new ResponseEntity<>(Optional.of(userSessionModel1), HttpStatus.OK);
            }
            return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e){
            return new ResponseEntity<>(Optional.empty(), HttpStatus.ALREADY_REPORTED);
        }
    }


}
