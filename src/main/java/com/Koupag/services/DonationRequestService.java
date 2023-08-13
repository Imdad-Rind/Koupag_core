package com.Koupag.services;

import com.Koupag.dtos.donation.CreateDonationDTO;
import com.Koupag.models.DonationRequest;
import com.Koupag.dtos.donation.EngagedDonationDTO;
import com.Koupag.dtos.donation.CompleteDonationDTO;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface DonationRequestService {
   public DonationRequest createNewDonationRequest(CreateDonationDTO request) throws NullPointerException, NoSuchElementException;
   public Optional<DonationRequest> getDonationRequestById(long id);
   public void updateVolunteerIdByDonationRequest(EngagedDonationDTO engagedDonationDTO) throws NoSuchElementException;
   public void updateRecipientIdByDonationRequest(CompleteDonationDTO completeDonationDTO) throws NoSuchElementException ,Exception;
}
