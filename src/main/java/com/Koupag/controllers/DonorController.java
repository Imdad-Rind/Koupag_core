package com.Koupag.controllers;

import com.Koupag.dtos.donation.CreateDonationDTO;
import com.Koupag.dtos.donation.previous_donation.DonationRequestDTO;
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
    public ResponseEntity<String> donationRequest(@RequestBody CreateDonationDTO request){
        try {
            donationRequestService.createNewDonationRequest(request);
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("Successfully created a Donation Request", HttpStatus.OK);
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

