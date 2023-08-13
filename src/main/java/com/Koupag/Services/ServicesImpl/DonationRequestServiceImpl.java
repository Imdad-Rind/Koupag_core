package com.Koupag.Services.ServicesImpl;

import com.Koupag.Model.DonationRequest;
import com.Koupag.Model.EngagedDonor;
import com.Koupag.Model.EngagedRecipient;
import com.Koupag.Model.RequestItem;
import com.Koupag.Repository.*;
import com.Koupag.Services.DonationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DonationRequestServiceImpl implements DonationRequestService {
    private final DonationRequestRepository repository;
    private final DonorRepository donorRepository;
    private final VolunteerRepository volunteerRepository;
    private final RecipientRepository recipientRepository;
    private final SurplusMaterialRepository surplusMaterialRepository;

    @Autowired
    public DonationRequestServiceImpl(DonationRequestRepository repository, DonorRepository donorRepository, VolunteerRepository volunteerRepository, RecipientRepository recipientRepository, SurplusMaterialRepository surplusMaterialRepository) {
        this.repository = repository;
        this.donorRepository = donorRepository;
        this.volunteerRepository = volunteerRepository;
        this.recipientRepository = recipientRepository;
        this.surplusMaterialRepository = surplusMaterialRepository;
    }

    @Override
    public DonationRequest createNewDonationRequest(Map<String, String> request) throws NullPointerException {
        DonationRequest dr = new DonationRequest();
        dr.setDonorId(donorRepository.findById(Long.decode(request.get("donorId"))).get());
        //dr.setRequestItemId(surplusMaterialRepository.findById(Long.decode(request.get("requestItemId"))).get());
        dr.setCreationDateAndTime(LocalDateTime.now());
        return repository.save(dr);
    }
    
    @Override
    public Optional<DonationRequest> getDonationRequestById(long id) {
        return repository.findById(id);
    }
    
    @Override
    public void updateVolunteerIdByDonationRequest(EngagedDonor engagedDonor) throws NoSuchElementException  {
        DonationRequest requestToBeUpdated = repository.getReferenceById(engagedDonor.getRequestId());
        requestToBeUpdated.setVolunteerId(volunteerRepository.findById(engagedDonor.getVolunteerId()).get());
        requestToBeUpdated.setEngagedDateAndTime(LocalDateTime.now());
        repository.save(requestToBeUpdated);
    }
    
    @Override
    public void updateRecipientIdByDonationRequest(EngagedRecipient engagedRecipient) throws NoSuchElementException {
        DonationRequest requestToBeUpdated = repository.getReferenceById(engagedRecipient.getRequestId());
        requestToBeUpdated.setRecipientId(recipientRepository.findById(engagedRecipient.getRecipientId()).get());
        requestToBeUpdated.setSuccessfulDonationDateAndTime(LocalDateTime.now());
        repository.save(requestToBeUpdated);
        
    }
    
    
}
