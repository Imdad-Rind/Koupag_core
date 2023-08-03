package com.Koupag.Controller;

import com.Koupag.Services.DonationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/donor/")
public class DonorController {


    private final DonationRequestService donationRequestService;

    @Autowired
    public DonorController(DonationRequestService donationRequestService) {
        this.donationRequestService = donationRequestService;

    }
    

    @PostMapping("request")
    public ResponseEntity<Map<String, String>> donationRequest(@RequestBody Map<String, String> request){
        System.out.println(request);
        donationRequestService.createNewDonationRequest(request);

        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
