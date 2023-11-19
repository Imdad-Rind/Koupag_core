package com.Koupag.controllers;

import com.Koupag.dtos.donation.CreateDonationDTO;
import com.Koupag.dtos.donation.previous_donation.DonationRequestDTO;
import com.Koupag.mappers.DonationMapper;
import com.Koupag.mappers.DonationRequestMapper;
import com.Koupag.models.DonationRequest;
import com.Koupag.repositories.DonationRequestRepository;
import com.Koupag.services.DonationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/donor/")
@PreAuthorize("hasRole('ROLE_DONOR')")
public class DonorController {
    
    
    final private DonationRequestService donationRequestService;
    final private DonationRequestMapper donationRequestMapper;

    @Autowired
    public DonorController(DonationRequestService donationRequestService, DonationRequestMapper donationRequestMapper) {
        this.donationRequestService = donationRequestService;
        this.donationRequestMapper = donationRequestMapper;
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
    
    @GetMapping("donations/{id}")
    public ResponseEntity<List<DonationMapper>>getAllDonations(@PathVariable(name = "id") Long id){
        List<DonationRequest> donationList = donationRequestService.getAllSuccessfulDonationRequestByDonorId(id);
        List<DonationMapper> data = donationList.stream().map(DonationMapper::new).toList();
        System.out.println(data);
//        List<DonationRequestDTO> mappedData = new ArrayList<>();
//         for (DonationRequest d : donationList){
//             mappedData.add(donationRequestMapper.fromDonationRequest(d));
//         }
        return new ResponseEntity<>( data,HttpStatus.OK);
    }
    
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


    @GetMapping("active_donation/{id}")
    public ResponseEntity<DonationMapper> activeDonation(@PathVariable(name = "id") Long id){
        try{
            DonationMapper donationRequest = new DonationMapper(donationRequestService.getActiveDonationRequestByDonorId(id));
            return new ResponseEntity<DonationMapper>(donationRequest,HttpStatus.OK);
//            return new ResponseEntity<>(new DonationRequest(),HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

