package com.Koupag.controllers;

import com.Koupag.dtos.user.userProfileDTO;
import com.Koupag.services.CitiesServices;
import com.Koupag.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/")
public class UserController {
	
	private final UserService userService;
	private final CitiesServices citiesServices;
	
	@Autowired
	public UserController(UserService userService, CitiesServices citiesServices) {
		this.userService = userService;
		this.citiesServices = citiesServices;
	}
	
	@PostMapping("profile")
	public ResponseEntity<Void> createUserProfile(@RequestBody userProfileDTO data){
		try {
			String city = data.getUserProfile().getAddress().getCity().toString();
			
			userService.createUserProfileByUserId(data.getUserProfile(), data.getUserID());
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
