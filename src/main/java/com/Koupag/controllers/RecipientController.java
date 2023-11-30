package com.Koupag.controllers;

import com.Koupag.mappers.DonationMapper;
import com.Koupag.mappers.DonationRequestMapper;
import com.Koupag.models.DonationRequest;
import com.Koupag.services.DonationRequestService;
import com.Koupag.services.RecipientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipient/")
//@PreAuthorize("hasRole('ROLE_RECIPIENT')")
public class RecipientController {

	private final RecipientService recipientService;
	private final DonationRequestService donationRequestService;
	private final DonationRequestMapper donationRequestMapper;

	public RecipientController(RecipientService recipientService, DonationRequestService donationRequestService, DonationRequestMapper donationRequestMapper) {
		this.recipientService = recipientService;
		this.donationRequestService = donationRequestService;
		this.donationRequestMapper = donationRequestMapper;
	}
	
	
	@GetMapping("donations/{id}")
	public List<DonationMapper> getAllDonationRequestByRecipient(@PathVariable(name = "id") Long id){
		List<DonationRequest> donationList = donationRequestService.getAllSuccessfulDonationRequestByDonorId(id);
		return donationList.stream().map(DonationMapper::new).toList();
	}

	@GetMapping("active-donations/{id}")
	public List<DonationMapper> getAllActiveDonationRequestByRecipient(@PathVariable(name = "id") Long id){
		List<DonationRequest> donationList = donationRequestService.getAllActiveDonationRequestByRecipientId(id);
		return donationList.stream().map(DonationMapper::new).toList();
	}
}