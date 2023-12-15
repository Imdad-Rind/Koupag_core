package com.Koupag.controllers;

import com.Koupag.dtos.donation.EngagedDonationDTO;
import com.Koupag.dtos.donation.CompleteDonationDTO;
import com.Koupag.mappers.DonationMapper;
import com.Koupag.models.DonationRequest;
import com.Koupag.services.DonationRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/volunteer/")
//@PreAuthorize("hasRole('ROLE_VOLUNTEER')")
public class VolunteerController {
	private final DonationRequestService donationRequestService;
	
	public VolunteerController(DonationRequestService donationRequestService) {
		this.donationRequestService = donationRequestService;
	}
	@PostMapping("pickup-donation")
	public ResponseEntity<Void> addVolunteerToRequestPickup(@RequestBody EngagedDonationDTO engagedDonationDTO){
		try{
			donationRequestService.updateVolunteerPickupByDonationRequest(engagedDonationDTO);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (NoSuchElementException e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("unpick-donation")
	public ResponseEntity<Void> removeVolunteerToRequestPickup(@RequestBody EngagedDonationDTO engagedDonationDTO){
		try{
			donationRequestService.removeVolunteerPickupByDonationRequest(engagedDonationDTO);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (NoSuchElementException e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("engage-donation")
	public ResponseEntity<Void> addVolunteerToRequestEngagement(@RequestBody EngagedDonationDTO engagedDonationDTO){
		try{
			donationRequestService.updateVolunteerEngagedTime(engagedDonationDTO);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (NoSuchElementException e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	@PostMapping("complete-donation")
	public ResponseEntity<Void> addVolunteerIdToRequest(@RequestBody CompleteDonationDTO completeDonationDTO){
		try{
			donationRequestService.updateRecipientByDonationRequest(completeDonationDTO);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (NoSuchElementException e){
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("Exception: "+e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping("donations/{id}")
	public List<DonationMapper> getAllDonations(@PathVariable(name = "id") Long id){
		List<DonationRequest> donationList = donationRequestService.getAllSuccessfulDonationRequestByVolunteerId(id);
		return donationList.stream().map(DonationMapper::new).toList();
	}

	@GetMapping("active_donations")
	public ResponseEntity<List<DonationMapper>>getAllActiveDonation(){
		return new ResponseEntity<>(
				donationRequestService.getAllActiveDonation().stream().map(DonationMapper::new).toList(),
				HttpStatus.OK
		);
	}
}
