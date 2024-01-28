package com.Koupag.controllers;

import com.Koupag.dtos.donation.EngagedDonationDTO;
import com.Koupag.dtos.donation.CompleteDonationDTO;
import com.Koupag.execptions.NoSuchElementExceptionWrapper;
import com.Koupag.execptions.UnknownError;
import com.Koupag.mappers.DonationMapper;
import com.Koupag.models.DonationRequest;
import com.Koupag.services.DonationRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/volunteer/",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//@PreAuthorize("hasRole('ROLE_VOLUNTEER')")
public class VolunteerController {
	private final DonationRequestService donationRequestService;


	
	public VolunteerController(DonationRequestService donationRequestService) {
		this.donationRequestService = donationRequestService;
	}

	@GetMapping("responded-donations/{id}")
	public ResponseEntity<DonationMapper> getRespondedDonations(@PathVariable(name = "id") UUID id){
		DonationRequest donationList = donationRequestService.getRespondedDonationOfVolunteer(id);
		return new ResponseEntity<>(new DonationMapper(donationList), donationList == null ? HttpStatus.NO_CONTENT: HttpStatus.OK);
	}


	@PostMapping("pickup-donation")
	public ResponseEntity<Void> addVolunteerToRequestPickup(@RequestBody EngagedDonationDTO engagedDonationDTO){
		try{
			donationRequestService.updateVolunteerPickupByDonationRequest(engagedDonationDTO);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (NoSuchElementException e){
			throw new NoSuchElementExceptionWrapper(" Requested Content is nonexistent ",e.getCause());
		} catch (Exception e){
			throw new UnknownError("Unknown Error : " + e.getMessage(),e.getCause(),e.getStackTrace());
		}
	}

	@PostMapping("unpick-donation")
	public ResponseEntity<Void> removeVolunteerToRequestPickup(@RequestBody EngagedDonationDTO engagedDonationDTO){
		try{
			donationRequestService.removeVolunteerPickupByDonationRequest(engagedDonationDTO);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (NoSuchElementException e){
			throw new NoSuchElementExceptionWrapper(" Requested Content is nonexistent ",e.getCause());
		} catch (Exception e){
			throw new UnknownError("Unknown Error : " + e.getMessage(),e.getCause(),e.getStackTrace());
		}
	}
	
	@PostMapping("engage-donation")
	public ResponseEntity<Void> addVolunteerToRequestEngagement(@RequestBody EngagedDonationDTO engagedDonationDTO){
		try{
			donationRequestService.updateVolunteerEngagedTime(engagedDonationDTO);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (NoSuchElementException e){
			throw new NoSuchElementExceptionWrapper(" Requested Content is nonexistent ",e.getCause());
		} catch (Exception e) {
			throw new UnknownError("Unknown Error : " + e.getMessage(),e.getCause(),e.getStackTrace());

        }
    }

	@PostMapping("complete-donation")
	public ResponseEntity<Void> addVolunteerIdToRequest(@RequestBody CompleteDonationDTO completeDonationDTO){
		try{
			donationRequestService.updateRecipientByDonationRequest(completeDonationDTO);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (NoSuchElementException e){
			throw new NoSuchElementExceptionWrapper(" Requested Content is nonexistent ",e.getCause());
		} catch (Exception e) {
			throw new UnknownError("Unknown Error : " + e.getMessage(),e.getCause(),e.getStackTrace());
        }
    }
	
	@GetMapping("donations/{id}")
	public ResponseEntity<List<DonationMapper>> getAllDonations(@PathVariable(name = "id") UUID id){
		List<DonationRequest> donationList = donationRequestService.getAllSuccessfulDonationRequestByVolunteerId(id);
		return new ResponseEntity<>(donationList.stream().map(DonationMapper::new).toList(), donationList.isEmpty() ? HttpStatus.NO_CONTENT: HttpStatus.OK);
	}

	@GetMapping("active_donations")
	public ResponseEntity<List<DonationMapper>>getAllActiveDonation(){
		return new ResponseEntity<>(
				donationRequestService.getAllActiveDonation().stream().map(DonationMapper::new).toList(),
				HttpStatus.OK
		);
	}
}
