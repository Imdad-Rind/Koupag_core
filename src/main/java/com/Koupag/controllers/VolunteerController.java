package com.Koupag.controllers;

import com.Koupag.models.EngagedDonor;
import com.Koupag.services.DonationRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/volunteer/")
public class VolunteerController {
	private final DonationRequestService donationRequestService;
	
	public VolunteerController(DonationRequestService donationRequestService) {
		this.donationRequestService = donationRequestService;
	}
	
	@PostMapping("engage")
	public ResponseEntity<String> addVolunteerIdToRequest(@RequestBody EngagedDonor engagedDonor){
		try{
			donationRequestService.updateVolunteerIdByDonationRequest(engagedDonor);
		} catch (NoSuchElementException e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("done", HttpStatus.OK);
	}
}
