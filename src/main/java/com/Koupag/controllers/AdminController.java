package com.Koupag.controllers;

import com.Koupag.models.SurplusMaterial;
import com.Koupag.services.SurplusMaterialServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminController {
private final SurplusMaterialServices materialServices;
	
	public AdminController(SurplusMaterialServices materialServices) {
		this.materialServices = materialServices;
	}
	@PostMapping("surplus")
	public ResponseEntity<SurplusMaterial> addNewSurplus(@RequestBody SurplusMaterial surplusMaterial){
		materialServices.createNewSurplusMaterial(surplusMaterial);
		return new ResponseEntity<>(surplusMaterial, HttpStatus.OK);
	}
}
