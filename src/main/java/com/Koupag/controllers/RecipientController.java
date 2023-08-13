package com.Koupag.controllers;

import com.Koupag.models.DonationRequest;
import com.Koupag.services.RecipientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipient/")
public class RecipientController {

	private final RecipientService recipientService;

	public RecipientController(RecipientService recipientService) {
		this.recipientService = recipientService;
	}

//	@PostMapping("donation-history")
//	public ResponseEntity<List<DonationRequest>> donations(@RequestBody long id){
//		return new ResponseEntity<>(recipientService.getPreviousRequests(id), HttpStatus.OK);
//	}

}