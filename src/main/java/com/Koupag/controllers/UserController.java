package com.Koupag.controllers;

import com.Koupag.dtos.NotificationDto;
import com.Koupag.dtos.login.UserDOS;
import com.Koupag.execptions.NoSuchUserExist;
import com.Koupag.execptions.UnknownError;
import com.Koupag.mappers.models_map.SurplusMaterialMap;
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

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping(path = "api/user/",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
            throw new UnknownError("Unknown Error : " + e.getMessage(),e.getCause(),e.getStackTrace());
        }
    }

    @GetMapping("/get-surplus-materials")
    public ResponseEntity<List<SurplusMaterialMap>> allSurplusMaterials(){
        List<SurplusMaterialMap> surplusMaterials = surplusMaterialServices.getAllSurplusMaterialsName().stream().map(SurplusMaterialMap::new).toList();
        if(!surplusMaterials.isEmpty()){
            return new ResponseEntity<>(surplusMaterials, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
    }
    @GetMapping("/get-profile/{id}")
    public ResponseEntity<UserMap> getUser(@PathVariable(name = "id") UUID id){
        User user;
        if(userService.getUserById(id).isPresent()){
            user = userService.getUserById(id).get();
        }
        else {
            throw new NoSuchUserExist("this user Does not exist");
        }
        UserMap updateUser = new UserMap(user);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }
    @PostMapping("/update-profile/{id}")
    public ResponseEntity<UserDOS> updateUserProfile(@PathVariable(name = "id") UUID id, @RequestBody User user){
        userService.updateUserById(id,user);
        User u;
        if(userService.getUserById(id).isPresent()){
            u = userService.getUserById(id).get();
        }
        else {
            throw new NoSuchUserExist("this user Does not exist");
        }
        UserDOS updatedUser = new UserDOS(u);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @GetMapping("turn-off-notifications/{id}")
    public ResponseEntity turnOffNotifications(@PathVariable(name = "id") UUID id){
        if(!userSessionService.turnOffNotification(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("turn-on-notifications/{id}")
    public ResponseEntity turnOnNotifications(@PathVariable(name = "id") UUID id){
        if(userSessionService.turnOnNotification(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get-notifications/{id}")
    public ResponseEntity<List<NotificationDto>> getNotifications(@PathVariable(name = "id") UUID id){
        List<NotificationDto> notifications = userService.getUserNotifications(id).stream().map(NotificationDto::new).toList();
        if(!notifications.isEmpty()){
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/delete-notification/{id}")
    public ResponseEntity deleteNotifications(@PathVariable(name = "id") UUID id){
        try {
            userService.deleteUserNotification(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ignored){

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
