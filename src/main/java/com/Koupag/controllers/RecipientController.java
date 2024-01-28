package com.Koupag.controllers;

import com.Koupag.mappers.DonationMapper;
import com.Koupag.models.DonationRequest;
import com.Koupag.services.DonationRequestService;
import com.Koupag.services.RecipientService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/recipient/",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//@PreAuthorize("hasRole('ROLE_RECIPIENT')")
public class RecipientController {

	private final RecipientService recipientService;
	private final DonationRequestService donationRequestService;

	public RecipientController(RecipientService recipientService, DonationRequestService donationRequestService) {
		this.recipientService = recipientService;
		this.donationRequestService = donationRequestService;
	}
	
	
	@GetMapping("donations/{id}")
	public List<DonationMapper> getAllDonationRequestByRecipient(@PathVariable(name = "id") UUID id){
		List<DonationRequest> donationList = donationRequestService.getAllDonationRequestByRecipientId(id);
		return donationList.stream().map(DonationMapper::new).toList();
	}

	@GetMapping("active-donations/{id}")
	public List<DonationMapper> getAllActiveDonationRequestByRecipient(@PathVariable(name = "id") UUID id){
		List<DonationRequest> donationList = donationRequestService.getAllActiveDonationRequestByRecipientId(id);
		return donationList.stream().map(DonationMapper::new).toList();
	}
}