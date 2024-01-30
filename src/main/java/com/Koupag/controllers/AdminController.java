package com.Koupag.controllers;

import com.Koupag.models.SurplusMaterial;
import com.Koupag.services.CitiesServices;
import com.Koupag.services.SurplusMaterialServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/admin")
public class AdminController {
private final SurplusMaterialServices materialServices;

	private final CitiesServices citiesServices;
	
	public AdminController(SurplusMaterialServices materialServices, CitiesServices citiesServices) {
		this.materialServices = materialServices;
		this.citiesServices = citiesServices;
	}
	@PostMapping(path = "add-surplus",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addNewSurplus(@RequestBody SurplusMaterial surplusMaterial){
		materialServices.createNewSurplusMaterial(surplusMaterial);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// ``````````````` Admin Dashboard section  ```````````````````````


	
	
}
