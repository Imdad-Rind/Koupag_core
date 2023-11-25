package com.Koupag.services;

import com.Koupag.dtos.donation.CreateDonationDTO;
import com.Koupag.models.DonationRequest;
import com.Koupag.dtos.donation.EngagedDonationDTO;
import com.Koupag.dtos.donation.CompleteDonationDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface DonationRequestService {
   public DonationRequest createNewDonationRequest(CreateDonationDTO request) throws NullPointerException, NoSuchElementException, Exception;
   public Optional<DonationRequest> getDonationRequestById(long id);
   public void updateVolunteerPickupByDonationRequest(EngagedDonationDTO engagedDonationDTO) throws Exception;
   public void removeVolunteerPickupByDonationRequest(EngagedDonationDTO engagedDonationDTO) throws Exception;
   public void updateVolunteerEngagedTime(EngagedDonationDTO engagedDonationDTO) throws Exception;
   public void updateRecipientByDonationRequest(CompleteDonationDTO completeDonationDTO) throws Exception;
   public List<DonationRequest> getAllSuccessfulDonationRequestByDonorId(Long donorId);
   public DonationRequest getActiveDonationRequestByDonorId(Long donorId) throws Exception;
   public List<DonationRequest> getAllSuccessfulDonationRequestByVolunteerId(Long volunteerId);
   public List<DonationRequest> getActiveDonationRequestByVolunteerId(Long volunteerId);
   public List<DonationRequest> getAllDonationRequestByRecipientId(Long recipientId);
   public List<DonationRequest> getAllActiveDonationRequestByRecipientId(Long recipientId);

   public  void closeActiveDonationById(Long id) throws Exception;
   public List<DonationRequest> getAllActiveDonation();
}
