package com.Koupag.controllers;

import com.Koupag.dtos.NotificationDto;
import com.Koupag.dtos.cities.cityDTO;
import com.Koupag.mappers.cityMapper;
import com.Koupag.models.City;
import com.Koupag.models.Roles;
import com.Koupag.services.CitiesServices;
import com.Koupag.services.FCMService;
import com.Koupag.services.RolesService;
import com.Koupag.services.SurplusMaterialServices;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/home")
public class HomeController {
    private final CitiesServices citiesServices;
    private final cityMapper cityMapper;
    private final SurplusMaterialServices surplusMaterialServices;
    private final RolesService rolesService;

    public HomeController(CitiesServices citiesServices, com.Koupag.mappers.cityMapper cityMapper, SurplusMaterialServices surplusMaterialServices, RolesService rolesService, FCMService fcmService) {
        this.citiesServices = citiesServices;
        this.cityMapper = cityMapper;
        this.surplusMaterialServices = surplusMaterialServices;
        this.rolesService = rolesService;
        this.fcmService = fcmService;
    }

    private final FCMService fcmService;

    
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

    @GetMapping("/send-a-notification")
    public ResponseEntity<String> testNotification(){
        Map<String, String> data= new HashMap<>();
        String result = "Oops sth went wrong";
        try {
            result = fcmService.sendANotification(
                    new NotificationDto(
                            "Komar have created",
                            "A clothe donation has created",
                            "https://media.istockphoto.com/id/1457889029/photo/group-of-food-with-high-content-of-dietary-fiber-arranged-side-by-side.jpg?s=1024x1024&w=is&k=20&c=96MkVCuqUWOcMZ7vO5nG41rPufiSWlayTac_nsxXUTw=",
                            data
                    ),
                    "fjXw6HB6QW6CRP9p2UHTbg:APA91bF3k8AfyD_0R2Ir8fxzcjnVbz6kV4aY21BwnUKUi5x6wOmGpTkYWn-ZDJJ5F-0m7-J3Ink3ZXukSMfxhgxBeFpI5sytyej_NXoL7XMzvx0DbKGYfu1zSPM9ax_3ufEX1PqedRDp"
            );
            return new ResponseEntity<>(result , HttpStatus.BAD_REQUEST);
        } catch (FirebaseMessagingException e) {
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
