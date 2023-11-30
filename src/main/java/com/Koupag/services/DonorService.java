package com.Koupag.services;

import com.Koupag.models.DonationRequest;
import com.Koupag.models.Recipient;

import java.util.List;

public interface DonorService {
   void create_donations(DonationRequest donationRequest);
   void close_donation();
   void see_donation_status();
   List<Recipient> findMostPoor(int count, String city);

}
