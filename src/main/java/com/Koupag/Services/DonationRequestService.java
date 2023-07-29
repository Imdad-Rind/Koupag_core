package com.Koupag.Services;

import com.Koupag.Model.DonationRequest;

import java.util.Map;

public interface DonationRequestService {

    DonationRequest createNewDonationRequest(Map<String, String> request);
}
