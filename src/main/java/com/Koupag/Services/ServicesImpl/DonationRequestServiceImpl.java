package com.Koupag.Services.ServicesImpl;

import com.Koupag.Model.DonationRequest;
import com.Koupag.Repository.DonationRequestRepository;
import com.Koupag.Services.DonationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationRequestServiceImpl implements DonationRequestService {
    private final DonationRequestRepository repository;
    @Autowired
    public DonationRequestServiceImpl(DonationRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public DonationRequest createNewDonationRequest(DonationRequest request) {
        return repository.save(request);
    }
}
