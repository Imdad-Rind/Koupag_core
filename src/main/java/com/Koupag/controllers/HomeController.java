package com.Koupag.controllers;

import com.Koupag.models.Cities;
import com.Koupag.models.Roles;
import com.Koupag.services.CitiesServices;
import com.Koupag.services.RolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/home")
public class HomeController {
    private final CitiesServices citiesServices;
    private final RolesService rolesService;
    
    public HomeController(CitiesServices citiesServices, RolesService rolesService) {
        this.citiesServices = citiesServices;
        this.rolesService = rolesService;
    }
    
    @GetMapping("cities")
    public ResponseEntity<List<Cities>>getAllCities(){
        return new ResponseEntity<>(citiesServices.getAllCities(), HttpStatus.OK);
    }
    @GetMapping("/userTypes")
    public ResponseEntity<List<Roles>> roles(){
        return new ResponseEntity<>(rolesService.getAllRoles(),HttpStatus.OK);
    }
}
