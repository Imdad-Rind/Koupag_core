package com.Koupag.controllers;

import com.Koupag.dtos.donation.EngagedDonationDTO;
import com.Koupag.dtos.donation.CompleteDonationDTO;
import com.Koupag.dtos.donation.previous_donation.DonationRequestDTO;
import com.Koupag.mappers.DonationRequestMapper;
import com.Koupag.models.DonationRequest;
import com.Koupag.services.DonationRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/volunteer/")
public class VolunteerController {
	private final DonationRequestService donationRequestService;
	private final DonationRequestMapper donationRequestMapper;
	
	public VolunteerController(DonationRequestService donationRequestService, DonationRequestMapper donationRequestMapper) {
		this.donationRequestService = donationRequestService;
		this.donationRequestMapper = donationRequestMapper;
	}
	
	@PostMapping("engage-donation")
	public ResponseEntity<String> addVolunteerIdToRequest(@RequestBody EngagedDonationDTO engagedDonationDTO){
		try{
			donationRequestService.updateVolunteerIdByDonationRequest(engagedDonationDTO);
			return new ResponseEntity<>("Donation Request have been engaged by: "+engagedDonationDTO.getVolunteerId(), HttpStatus.OK);
		} catch (NoSuchElementException e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("complete-donation")
	public ResponseEntity<String> addVolunteerIdToRequest(@RequestBody CompleteDonationDTO completeDonationDTO){
		try{
			donationRequestService.updateRecipientIdByDonationRequest(completeDonationDTO);
			return new ResponseEntity<>("Successful Donation", HttpStatus.OK);
		} catch (NoSuchElementException e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping("donations/{id}")
	public List<DonationRequestDTO> getAllDonations(@PathVariable(name = "id") Long id){
		var donationList = donationRequestService.getAllDonationRequestByDonorId(id);
		List<DonationRequestDTO> mappedData = new ArrayList<>();
		for (DonationRequest d : donationList){
			mappedData.add(donationRequestMapper.fromDonationRequest(d));
		}
		return mappedData;
	}
}
