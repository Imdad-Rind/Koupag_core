package com.Koupag.controllers;
import com.Koupag.dtos.cities.cityDTO;
import com.Koupag.mappers.cityMapper;
import com.Koupag.mappers.models_map.UserMap;
import com.Koupag.models.City;
import com.Koupag.models.Roles;
import com.Koupag.models.User;
import com.Koupag.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/home")
public class HomeController {
    private final CitiesServices citiesServices;
    private final cityMapper cityMapper;
    private final SurplusMaterialServices surplusMaterialServices;
    private final RolesService rolesService;
    private final UserService userService;
    private final UserSessionService userSessionService;
    
    public HomeController(CitiesServices citiesServices, com.Koupag.mappers.cityMapper cityMapper, SurplusMaterialServices surplusMaterialServices, RolesService rolesService, UserService userService, UserSessionService userSessionService) {
        this.citiesServices = citiesServices;
        this.cityMapper = cityMapper;
        this.surplusMaterialServices = surplusMaterialServices;
        this.rolesService = rolesService;
        this.userService = userService;
        this.userSessionService = userSessionService;
    }
    @GetMapping("cities")
    public ResponseEntity<List<cityDTO>>getAllCities(){
        var cities = citiesServices.getAllCities();
        List<cityDTO> mappedCityData= new ArrayList<>();
        for (City c : cities){
            mappedCityData.add(cityMapper.fromCityToDTO(c));
        }
        return new ResponseEntity<>(mappedCityData, HttpStatus.OK);
    }

    @GetMapping("/get-surplus-materials")
    public ResponseEntity<List<String>> allSurplusMaterials(){
        List<String> surplusMaterials = surplusMaterialServices.getAllSurplusMaterialsName();
        if(!surplusMaterials.isEmpty()){
            return new ResponseEntity<>(surplusMaterials, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
    }
    @GetMapping("/userTypes")
    public ResponseEntity<List<Roles>> roles(){
        return new ResponseEntity<>(rolesService.getAllRoles(),HttpStatus.OK);
    }
    @GetMapping("/get-profile/{id}")
    public ResponseEntity<UserMap> getUser(@PathVariable(name = "id")Long id){
       UserMap updateUser = new UserMap(userService.getUserById(id).get());
       return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }
    @PostMapping("/update-profile/{id}")
    public ResponseEntity<UserMap> updateUserProfile(@PathVariable(name = "id")Long id,@RequestBody User user){
        userService.updateUserById(id,user);
        UserMap updatedUser = new UserMap(userService.getUserById(id).get());
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @GetMapping("turn-off-notifications/{id}")
    public ResponseEntity turnOffNotifications(@PathVariable(name = "id")Long id){
        if(!userSessionService.turnOffNotification(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("turn-on-notifications/{id}")
    public ResponseEntity turnOnNotifications(@PathVariable(name = "id")Long id){
        if(userSessionService.turnOnNotification(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
