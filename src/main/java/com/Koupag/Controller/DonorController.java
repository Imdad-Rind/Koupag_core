package com.Koupag.Controller;

import com.Koupag.Services.DonationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/donor/")
public class DonorController {


    private DonationRequestService donationRequestService;

    @Autowired
    public DonorController(DonationRequestService donationRequestService) {
        this.donationRequestService = donationRequestService;

    }
    

    @PostMapping("request")
    public ResponseEntity<Map<String, String>> donationRequest(@RequestBody Map<String, String> request){
        System.out.println(request);
        try {
            donationRequestService.createNewDonationRequest(request);
        } catch (NullPointerException e){
            Map<String,String> error = new HashMap<String, String>();
            error.put("message",e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
