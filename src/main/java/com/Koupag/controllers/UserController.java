package com.Koupag.controllers;

import com.Koupag.dtos.user.userProfileDTO;
import com.Koupag.models.UserProfile;
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
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("profile")
	public ResponseEntity<String> createUserProfile(@RequestBody userProfileDTO data){
		try {
			userService.createUserProfileByUserId(data.getUserProfile(), data.getUserID());
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		
		return new ResponseEntity<>("user profile created", HttpStatus.OK);
	}
}
