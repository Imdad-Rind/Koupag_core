package com.Koupag.Controller;

import com.Koupag.DTO.DonationRequestDTO;
import com.Koupag.DTO.SurplusMaterialDTO;
import com.Koupag.Mappers.DonationRequestMapper;
import com.Koupag.Services.DonationRequestService;
import com.Koupag.Services.SurplusMaterialServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/donation/")
public class DonationController {

    private final SurplusMaterialServices surplusMaterialServices;
    private final DonationRequestService donationRequestService;
    private final DonationRequestMapper donationRequestMapper;

    @Autowired
    public DonationController(SurplusMaterialServices surplusMaterialServices,
                              DonationRequestService donationRequestService, DonationRequestMapper donationRequestMapper) {
        this.surplusMaterialServices = surplusMaterialServices;
        this.donationRequestService = donationRequestService;
        this.donationRequestMapper = donationRequestMapper;
    }

    @PostMapping("surplus")
    public ResponseEntity<SurplusMaterialDTO> surplusMaterialRequest(@RequestBody SurplusMaterialDTO surplusMaterialDTO){
        surplusMaterialServices.newSurplusMaterialDonationRequest(surplusMaterialDTO);
        return new ResponseEntity<>(surplusMaterialDTO, HttpStatus.OK);
    }

    @PostMapping("request")
    public ResponseEntity<DonationRequestDTO> donationRequest(@RequestBody DonationRequestDTO donationRequestDTO){
        donationRequestService.createNewDonationRequest(donationRequestMapper.DtoToDonationRequestModel(donationRequestDTO));
        return new ResponseEntity<>(donationRequestDTO, HttpStatus.OK);
    }
}
