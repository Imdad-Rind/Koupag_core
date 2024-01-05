package com.Koupag.services;

import com.Koupag.dtos.donation.CreateDonationDTO;
import com.Koupag.models.DonationRequest;
import com.Koupag.dtos.donation.EngagedDonationDTO;
import com.Koupag.dtos.donation.CompleteDonationDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public interface DonationRequestService {
   public DonationRequest getRespondedDonationOfVolunteer(UUID id);
   public void createNewDonationRequest(CreateDonationDTO request) throws NullPointerException, NoSuchElementException, Exception;
   public Optional<DonationRequest> getDonationRequestById(UUID id);
   public void updateVolunteerPickupByDonationRequest(EngagedDonationDTO engagedDonationDTO) throws Exception;
   public void removeVolunteerPickupByDonationRequest(EngagedDonationDTO engagedDonationDTO) throws Exception;
   public void updateVolunteerEngagedTime(EngagedDonationDTO engagedDonationDTO) throws Exception;
   public void updateRecipientByDonationRequest(CompleteDonationDTO completeDonationDTO) throws Exception;
   public List<DonationRequest> getAllSuccessfulDonationRequestByDonorId(UUID donorId);
   public DonationRequest getActiveDonationRequestByDonorId(UUID donorId) throws Exception;
   public List<DonationRequest> getAllSuccessfulDonationRequestByVolunteerId(UUID volunteerId);
   public List<DonationRequest> getActiveDonationRequestByVolunteerId(UUID volunteerId);
   public List<DonationRequest> getAllDonationRequestByRecipientId(UUID recipientId);
   public List<DonationRequest> getAllActiveDonationRequestByRecipientId(UUID recipientId);

   public  void closeActiveDonationById(UUID id) throws Exception;
   public List<DonationRequest> getAllActiveDonation();
}
