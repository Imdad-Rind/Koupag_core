package com.Koupag.Services;

import com.Koupag.Model.DonationRequest;
import com.Koupag.Model.EngagedDonor;

import java.util.Map;
import java.util.Optional;

public interface DonationRequestService {

   public DonationRequest createNewDonationRequest(Map<String, String> request);
   
   public Optional<DonationRequest> getDonationRequestById(long id);
   
   public void updateVolunteerIdByDonationRequest(EngagedDonor engagedDonor);
    public void updateRecipientIdByDonationRequest(long requestId, long recipientId);
}
