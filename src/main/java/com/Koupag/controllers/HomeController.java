package com.Koupag.controllers;
import com.Koupag.dtos.cities.cityDTO;
import com.Koupag.mappers.cityMapper;
import com.Koupag.mappers.models_map.UserMap;
import com.Koupag.models.City;
import com.Koupag.models.Roles;
import com.Koupag.models.User;
import com.Koupag.services.CitiesServices;
import com.Koupag.services.RolesService;
import com.Koupag.services.SurplusMaterialServices;
import com.Koupag.services.UserService;
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
    private final RolesService rolesService;
    private final UserService userService;
    
    public HomeController(CitiesServices citiesServices, com.Koupag.mappers.cityMapper cityMapper,  RolesService rolesService, UserService userService) {
        this.citiesServices = citiesServices;
        this.cityMapper = cityMapper;
        this.rolesService = rolesService;
        this.userService = userService;
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


    @GetMapping("/userTypes")
    public ResponseEntity<List<Roles>> roles(){
        return new ResponseEntity<>(rolesService.getAllRoles(),HttpStatus.OK);
    }

}
