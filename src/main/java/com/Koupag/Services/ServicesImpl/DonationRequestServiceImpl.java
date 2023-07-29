package com.Koupag.Services.ServicesImpl;

import com.Koupag.Model.DonationRequest;
import com.Koupag.Model.Donor;
import com.Koupag.Repository.DonationRequestRepository;
import com.Koupag.Repository.DonorRepository;
import com.Koupag.Repository.SurplusMaterialRepository;
import com.Koupag.Services.DonationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class DonationRequestServiceImpl implements DonationRequestService {
    private final DonationRequestRepository repository;
    private final DonorRepository donorRepository;
    private final SurplusMaterialRepository surplusMaterialRepository;

    @Autowired
    public DonationRequestServiceImpl(DonationRequestRepository repository, DonorRepository donorRepository, SurplusMaterialRepository surplusMaterialRepository) {
        this.repository = repository;
        this.donorRepository = donorRepository;
        this.surplusMaterialRepository = surplusMaterialRepository;
    }

    @Override
    public DonationRequest createNewDonationRequest(Map<String, String> request) {
        DonationRequest dr = new DonationRequest();
        dr.setDonorId(donorRepository.findById(Long.decode(request.get("donorId"))).get());
        dr.setRequestItemId(surplusMaterialRepository.findById(Long.decode(request.get("requestItemId"))).get());
        dr.setCreationDateAndTime(LocalDateTime.now());
        return repository.save(dr);
    }
}
