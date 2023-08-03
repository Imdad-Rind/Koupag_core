package com.Koupag.Controller;

import com.Koupag.Model.EngagedDonor;
import com.Koupag.Services.DonationRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/volunteer/")
public class VolunteerController {
	private final DonationRequestService donationRequestService;
	
	public VolunteerController(DonationRequestService donationRequestService) {
		this.donationRequestService = donationRequestService;
	}
	
	@PostMapping("insert")
	public ResponseEntity<String> addVolunteerIdToRequest(@RequestBody EngagedDonor engagedDonor){
		donationRequestService.updateVolunteerIdByDonationRequest(engagedDonor);
		return new ResponseEntity<>("done", HttpStatus.OK);
	}
}
