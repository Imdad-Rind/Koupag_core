package com.Koupag.Services;

import com.Koupag.Model.DonationRequest;
import com.Koupag.Model.EngagedDonor;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface DonationRequestService {

   public DonationRequest createNewDonationRequest(Map<String, String> request) throws NullPointerException;
   
   public Optional<DonationRequest> getDonationRequestById(long id);
   
   public void updateVolunteerIdByDonationRequest(EngagedDonor engagedDonor) throws NoSuchElementException;
    public void updateRecipientIdByDonationRequest(long requestId, long recipientId);
}
