package com.Koupag.services;

import com.Koupag.models.DonationRequest;
import com.Koupag.models.EngagedDonor;
import com.Koupag.models.EngagedRecipient;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface DonationRequestService {

   public DonationRequest createNewDonationRequest(Map<String, String> request) throws NullPointerException;
   
   public Optional<DonationRequest> getDonationRequestById(long id);
   
   public void updateVolunteerIdByDonationRequest(EngagedDonor engagedDonor) throws NoSuchElementException;
    public void updateRecipientIdByDonationRequest(EngagedRecipient engagedRecipient);
}
