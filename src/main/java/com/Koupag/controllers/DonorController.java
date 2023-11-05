package com.Koupag.controllers;

import com.Koupag.dtos.donation.CreateDonationDTO;
import com.Koupag.dtos.donation.previous_donation.DonationRequestDTO;
import com.Koupag.dtos.donation.previous_donation.activeDonationFilterDTO;
import com.Koupag.mappers.DonationRequestMapper;
import com.Koupag.models.DonationRequest;
import com.Koupag.repositories.DonationRequestRepository;
import com.Koupag.services.DonationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/donor/")
public class DonorController {
    
    
    final private DonationRequestService donationRequestService;
    final private DonationRequestRepository repository;
    final private DonationRequestMapper donationRequestMapper;

    @Autowired
    public DonorController(DonationRequestService donationRequestService, DonationRequestRepository repository, DonationRequestMapper donationRequestMapper) {
        this.donationRequestService = donationRequestService;
        
        this.repository = repository;
        this.donationRequestMapper = donationRequestMapper;
    }
    

    @PostMapping("create-donation")
    public ResponseEntity<Void> donationRequest(@RequestBody CreateDonationDTO request){
        try {
            donationRequestService.createNewDonationRequest(request);
        }
        catch (NullPointerException | NoSuchElementException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        } catch(Exception e){
            System.out.println(e.getMessage());
            
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("donations/{id}")
    public ResponseEntity<List<DonationRequestDTO>>getAllDonations(@PathVariable(name = "id") Long id){
        var donationList = donationRequestService.getAllDonationRequestByDonorId(id);
        List<DonationRequestDTO> mappedData = new ArrayList<>();
         for (DonationRequest d : donationList){
             mappedData.add(donationRequestMapper.fromDonationRequest(d));
         }
        return new ResponseEntity<>( mappedData,HttpStatus.OK);
    }
    
    @PostMapping("close_donation/{id}")
    public ResponseEntity<Void> closeActiveDonation(@PathVariable(name = "id") Long id){
        donationRequestService.closeActiveDonationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("active_donations")
    public ResponseEntity<List<activeDonationFilterDTO>>getAllActiveDonation(){
        var activeDonation = donationRequestService.getAllActiveDonation();
        List<activeDonationFilterDTO> mappedActiveDonations = new ArrayList<>();
        for (DonationRequest d : activeDonation){
            mappedActiveDonations.add(donationRequestMapper.fromActiveDonation(d));
        }
        return new ResponseEntity<>(mappedActiveDonations, HttpStatus.OK);
        
    }
}

