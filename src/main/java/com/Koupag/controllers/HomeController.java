package com.Koupag.controllers;

import com.Koupag.dtos.cities.cityDTO;
import com.Koupag.mappers.cityMapper;
import com.Koupag.models.City;
import com.Koupag.models.Roles;
import com.Koupag.services.CitiesServices;
import com.Koupag.services.RolesService;
import com.Koupag.services.SurplusMaterialServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/home")
public class HomeController {
    private final CitiesServices citiesServices;
    private final cityMapper cityMapper;
    private final SurplusMaterialServices surplusMaterialServices;
    private final RolesService rolesService;
    
    public HomeController(CitiesServices citiesServices, com.Koupag.mappers.cityMapper cityMapper, SurplusMaterialServices surplusMaterialServices, RolesService rolesService) {
        this.citiesServices = citiesServices;
        this.cityMapper = cityMapper;
        this.surplusMaterialServices = surplusMaterialServices;
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
}
