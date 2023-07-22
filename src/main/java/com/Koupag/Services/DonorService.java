package com.Koupag.Services;

import com.Koupag.Model.DonationRequest;

public interface DonorService {
   void create_donations(DonationRequest donationRequest);
   void close_donation();
   void see_donation_status();

}
