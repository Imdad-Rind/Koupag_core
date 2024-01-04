package com.Koupag.controllers;

import com.Koupag.dtos.login.UserDOS;
import com.Koupag.mappers.models_map.UserMap;
import com.Koupag.models.User;
import com.Koupag.models.UserSessionModel;
import com.Koupag.services.SurplusMaterialServices;
import com.Koupag.services.UserService;
import com.Koupag.services.UserSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/user/")
public class UserController {


    private final UserService userService;
    private final SurplusMaterialServices surplusMaterialServices;


    public UserController(UserService userService, SurplusMaterialServices surplusMaterialServices, UserSessionService userSessionService) {
        this.userService = userService;
        this.surplusMaterialServices = surplusMaterialServices;
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

    @GetMapping("/get-surplus-materials")
    public ResponseEntity<List<String>> allSurplusMaterials(){
        List<String> surplusMaterials = surplusMaterialServices.getAllSurplusMaterialsName();
        if(!surplusMaterials.isEmpty()){
            return new ResponseEntity<>(surplusMaterials, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
    }
    @GetMapping("/get-profile/{id}")
    public ResponseEntity<UserMap> getUser(@PathVariable(name = "id")Long id){
        UserMap updateUser = new UserMap(userService.getUserById(id).get());
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }
    @PostMapping("/update-profile/{id}")
    public ResponseEntity<UserDOS> updateUserProfile(@PathVariable(name = "id")Long id, @RequestBody User user){
        userService.updateUserById(id,user);
        UserDOS updatedUser = new UserDOS(userService.getUserById(id).get());
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

}
