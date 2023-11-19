package com.Koupag.controllers;

import com.Koupag.dtos.donation.previous_donation.DonationRequestDTO;
import com.Koupag.mappers.DonationRequestMapper;
import com.Koupag.models.DonationRequest;
import com.Koupag.services.DonationRequestService;
import com.Koupag.services.RecipientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/recipient/")
@PreAuthorize("hasRole('ROLE_RECIPIENT')")
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
	public List<DonationRequestDTO> getAllDonations(@PathVariable(name = "id") Long id){
		var donationList = donationRequestService.getAllSuccessfulDonationRequestByDonorId(id);
		List<DonationRequestDTO> mappedData = new ArrayList<>();
		for (DonationRequest d : donationList){
			mappedData.add(donationRequestMapper.fromDonationRequest(d));
		}
		return mappedData;
	}

}