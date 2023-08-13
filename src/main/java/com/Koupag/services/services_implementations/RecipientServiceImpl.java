package com.Koupag.services.services_implementations;

import com.Koupag.models.DonationRequest;
import com.Koupag.repositories.DonationRequestRepository;
import com.Koupag.services.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecipientServiceImpl implements RecipientService {
    private final DonationRequestRepository donationRequestRepository;
    @Autowired
    public RecipientServiceImpl(DonationRequestRepository donationRequestRepository) {
        this.donationRequestRepository = donationRequestRepository;
    }
//    @Override
//    public List<DonationRequest> getPreviousRequests(Long id) {
//        return donationRequestRepository.findAllByRecipientId(id);
//    }
}
