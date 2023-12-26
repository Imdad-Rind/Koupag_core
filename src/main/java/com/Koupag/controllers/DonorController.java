package com.Koupag.controllers;

import com.Koupag.dtos.donation.CreateDonationDTO;
import com.Koupag.mappers.DonationMapper;
import com.Koupag.models.DonationRequest;
import com.Koupag.services.DonationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "api/donor/")
//@PreAuthorize("hasRole('ROLE_DONOR')")
public class DonorController {
    
    
    final private DonationRequestService donationRequestService;

    @Autowired
    public DonorController(DonationRequestService donationRequestService  ) {
        this.donationRequestService = donationRequestService;
    }
    

    @PostMapping("create-donation")
    public ResponseEntity<Void> donationRequest(@RequestBody CreateDonationDTO request){
        try {
            donationRequestService.createNewDonationRequest(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NullPointerException | NoSuchElementException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Previous Donations
    @GetMapping("donations/{id}")
    public ResponseEntity<List<DonationMapper>>getAllDonations(@PathVariable(name = "id") Long id){
        final List<DonationRequest> donationList = donationRequestService.getAllSuccessfulDonationRequestByDonorId(id);
        final List<DonationMapper> data = donationList.stream().map(DonationMapper::new).toList();
        return new ResponseEntity<>( data,HttpStatus.OK);
    }
    // closes an opened donation request
    @PostMapping("close_donation/{id}")
    public ResponseEntity<Void> closeActiveDonation(@PathVariable(name = "id") Long id){
        try {
            donationRequestService.closeActiveDonationById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("The Error is: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get the opened donation requests
    @GetMapping("active_donation/{id}")
    public ResponseEntity<DonationMapper> activeDonation(@PathVariable(name = "id") Long id){
        try{
            DonationMapper donationRequest = new DonationMapper(donationRequestService.getActiveDonationRequestByDonorId(id));
            return new ResponseEntity<>(donationRequest,HttpStatus.OK);
//            return new ResponseEntity<>(new DonationRequest(),HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

