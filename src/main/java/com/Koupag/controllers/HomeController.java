package com.Koupag.controllers;
import com.Koupag.dtos.cities.cityDTO;
import com.Koupag.mappers.cityMapper;
import com.Koupag.models.City;
import com.Koupag.models.Roles;
import com.Koupag.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/home",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {
    private final CitiesServices citiesServices;
    private final cityMapper cityMapper;
    private final RolesService rolesService;

    public HomeController(CitiesServices citiesServices, com.Koupag.mappers.cityMapper cityMapper,  RolesService rolesService) {

        this.citiesServices = citiesServices;
        this.cityMapper = cityMapper;
        this.rolesService = rolesService;
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
        return new ResponseEntity<>(rolesService.getOnlyMainThreeRoles(),HttpStatus.OK);
    }
}
