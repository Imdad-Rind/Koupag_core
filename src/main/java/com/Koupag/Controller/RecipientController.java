package com.Koupag.Controller;

import com.Koupag.Model.EngagedDonor;
import com.Koupag.Model.EngagedRecipient;
import com.Koupag.Services.DonationRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/recipient/")
public class RecipientController {
	
	private final DonationRequestService donationRequestService;
	
	
	public RecipientController(DonationRequestService donationRequestService) {
		this.donationRequestService = donationRequestService;
	}
	
	@PostMapping("engage")
	public ResponseEntity<String> addVolunteerIdToRequest(@RequestBody EngagedRecipient engagedRecipient){
		try{
			donationRequestService.updateRecipientIdByDonationRequest(engagedRecipient);
		} catch (NoSuchElementException e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("done", HttpStatus.OK);
	}
}