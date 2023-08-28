package com.Koupag.services.services_implementations;

import com.Koupag.dtos.donation.CreateDonationDTO;
import com.Koupag.models.*;
import com.Koupag.dtos.donation.EngagedDonationDTO;
import com.Koupag.dtos.donation.CompleteDonationDTO;
import com.Koupag.repositories.*;
import com.Koupag.services.DonationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DonationRequestServiceImpl implements DonationRequestService {
    private final DonationRequestRepository repository;
    private final DonorRepository donorRepository;
    private final VolunteerRepository volunteerRepository;
    private final RecipientRepository recipientRepository;
    private final SurplusMaterialRepository surplusMaterialRepository;
    private final RequestItemRepository requestItemRepository;
    @Autowired
    public DonationRequestServiceImpl(DonationRequestRepository repository,
                                      DonorRepository donorRepository, VolunteerRepository volunteerRepository,
                                      RecipientRepository recipientRepository, SurplusMaterialRepository surplusMaterialRepository,
                                      RequestItemRepository requestItemRepository) {
        this.repository = repository;
        this.donorRepository = donorRepository;
        this.volunteerRepository = volunteerRepository;
        this.recipientRepository = recipientRepository;
        this.surplusMaterialRepository = surplusMaterialRepository;
        this.requestItemRepository = requestItemRepository;
    }

    @Override
    public DonationRequest createNewDonationRequest(CreateDonationDTO request) throws NullPointerException, NoSuchElementException {
        DonationRequest dr = new DonationRequest();
        dr.setDonor(donorRepository.findById(request.getDonorId()).get());
        final RequestItem requestItemTemp = new RequestItem(
                request.getCount(),
                surplusMaterialRepository.findById(request.getSurplusMaterialId()).get()
        );
        requestItemRepository.save(requestItemTemp);
        dr.setRequestItem(requestItemTemp);
        dr.setCreationDateAndTime(LocalDateTime.now());
        
        return repository.save(dr);
    }
    
    @Override
    public Optional<DonationRequest> getDonationRequestById(long id) {
        return repository.findById(id);
    }
    
    @Override
    public void updateVolunteerIdByDonationRequest(EngagedDonationDTO engagedDonationDTO) throws NoSuchElementException  {
        DonationRequest requestToBeUpdated = repository.getReferenceById(engagedDonationDTO.getRequestId());
        
        Donor donor = donorRepository.findById(requestToBeUpdated.getDonor().getId()).get();
        donor.setLastServed(LocalDate.now());
        donorRepository.save(donor);
        
        requestToBeUpdated.setVolunteer(volunteerRepository.findById(engagedDonationDTO.getVolunteerId()).get());
        requestToBeUpdated.setEngagedDateAndTime(LocalDateTime.now());
        repository.save(requestToBeUpdated);
    }
    
    @Override
    public void updateRecipientIdByDonationRequest(CompleteDonationDTO completeDonationDTO) throws NoSuchElementException, Exception {
        DonationRequest requestToBeUpdated = repository.getReferenceById(completeDonationDTO.getRequestId());
        
        Volunteer volunteer = volunteerRepository.findById(completeDonationDTO.getVolunteerId()).get();
        volunteer.setLastServed(LocalDate.now());
        volunteerRepository.save(volunteer);
        Recipient recipient = recipientRepository.findById(completeDonationDTO.getRecipientId()).get();
        recipient.setLastServed(LocalDate.now());
        recipientRepository.save(recipient);
        
        if(requestToBeUpdated.getVolunteer().getId() != completeDonationDTO.getVolunteerId()){
            throw new Exception("Please verify the recipient, donation isn't made to Recipient");
        }
        
        requestToBeUpdated.setRecipient(recipientRepository.findById(completeDonationDTO.getRecipientId()).get());
        requestToBeUpdated.setSuccessfulDonationDateAndTime(LocalDateTime.now());
        repository.save(requestToBeUpdated);
        
    }
    
    @Override
    public List<DonationRequest> getAllDonationRequestByDonorId(Long donorId) {
	    return repository.findDonationRequestsByDonorId(donorId);
    }
    
    @Override
    public List<DonationRequest> getAllDonationRequestByVolunteerId(Long volunteerId) {
        return repository.findDonationRequestsByVolunteerId(volunteerId);
    }
    
    @Override
    public List<DonationRequest> getAllDonationRequestByRecipientId(Long recipientId) {
        return repository.findDonationRequestsByRecipientId(recipientId);
    }
    
    
}
