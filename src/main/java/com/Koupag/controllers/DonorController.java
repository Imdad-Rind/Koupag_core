package com.Koupag.controllers;

import com.Koupag.dtos.donation.CreateDonationDTO;
import com.Koupag.services.DonationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/donor/")
public class DonorController {


    final private DonationRequestService donationRequestService;

    @Autowired
    public DonorController(DonationRequestService donationRequestService) {
        this.donationRequestService = donationRequestService;

    }
    

    @PostMapping("create-donation")
    public ResponseEntity<String> donationRequest(@RequestBody CreateDonationDTO request){
        try {
            donationRequestService.createNewDonationRequest(request);
        } catch (NullPointerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully created a Donation Request", HttpStatus.OK);
    }
}
