package com.Koupag.Mappers;

import com.Koupag.DTO.DonationRequestDTO;
import com.Koupag.Model.DonationRequest;
import org.springframework.stereotype.Component;

@Component
public class DonationRequestMapper {

    public DonationRequest DtoToDonationRequestModel(DonationRequestDTO requestDTO){
        DonationRequest donationRequest = new DonationRequest();
        donationRequest.setDonorId(requestDTO.getDonorId());
        donationRequest.setRecipientId(requestDTO.getRecipientId());
        donationRequest.setVolunteerId(requestDTO.getVolunteerId());
        donationRequest.setRequestItemId(requestDTO.getRequestItemId());
        donationRequest.setStatus(requestDTO.getStatus());
        donationRequest.setCreationDateAndTime(requestDTO.getCreationDateAndTime());
        donationRequest.setEngagedDateAndTime(requestDTO.getEngagedDateAndTime());
        donationRequest.setSuccessfulDonationDateAndTime(requestDTO.getSuccessfulDonationDateAndTime());
        return donationRequest;
    }
}
