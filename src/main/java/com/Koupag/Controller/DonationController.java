package com.Koupag.Controller;

import com.Koupag.Model.DonationRequest;
import com.Koupag.Model.SurplusMaterial;
import com.Koupag.Services.DonationRequestService;
import com.Koupag.Services.SurplusMaterialServices;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/donation/")
public class DonationController {

    private final SurplusMaterialServices surplusMaterialServices;
    private final DonationRequestService donationRequestService;

    @Autowired
    public DonationController(SurplusMaterialServices surplusMaterialServices,
                              DonationRequestService donationRequestService) {
        this.surplusMaterialServices = surplusMaterialServices;
        this.donationRequestService = donationRequestService;

    }

    @PostMapping("surplus")
    public ResponseEntity<SurplusMaterial> surplusMaterialRequest(@RequestBody SurplusMaterial surplusMaterial){
        surplusMaterialServices.newSurplusMaterialDonationRequest(surplusMaterial);
        return new ResponseEntity<>(surplusMaterial, HttpStatus.OK);
    }

    @PostMapping("request")
    public ResponseEntity<Map<String, String>> donationRequest(@RequestBody Map<String, String> request){
        System.out.println(request);
        donationRequestService.createNewDonationRequest(request);

        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
