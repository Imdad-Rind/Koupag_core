package com.Koupag.services;

import com.Koupag.models.DonationRequest;

public interface DonorService {
   void create_donations(DonationRequest donationRequest);
   void close_donation();
   void see_donation_status();

}
