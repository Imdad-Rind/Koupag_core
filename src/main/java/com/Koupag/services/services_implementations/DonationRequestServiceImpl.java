package com.Koupag.services.services_implementations;

import com.Koupag.dtos.donation.CreateDonationDTO;
import com.Koupag.models.*;
import com.Koupag.dtos.donation.EngagedDonationDTO;
import com.Koupag.dtos.donation.CompleteDonationDTO;
import com.Koupag.repositories.*;
import com.Koupag.services.DonationRequestService;
import com.Koupag.services.DonorService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DonationRequestServiceImpl implements DonationRequestService {
    private final DonationRequestRepository donationRequestRepository;
    private final RecipientDonationRepository recipientDonationRepository;
    private final DonorRepository donorRepository;
    private final VolunteerRepository volunteerRepository;
    private final RecipientRepository recipientRepository;
    private final SurplusMaterialRepository surplusMaterialRepository;
    private final RequestItemRepository requestItemRepository;
    private final DonorService donorService;
    @Autowired
    public DonationRequestServiceImpl(DonationRequestRepository donationRequestRepository,
                                      RecipientDonationRepository recipientDonationRepository, DonorRepository donorRepository, VolunteerRepository volunteerRepository,
                                      RecipientRepository recipientRepository, SurplusMaterialRepository surplusMaterialRepository,
                                      RequestItemRepository requestItemRepository, DonorService donorService) {
        this.donationRequestRepository = donationRequestRepository;
        this.recipientDonationRepository = recipientDonationRepository;
        this.donorRepository = donorRepository;
        this.volunteerRepository = volunteerRepository;
        this.recipientRepository = recipientRepository;
        this.surplusMaterialRepository = surplusMaterialRepository;
        this.requestItemRepository = requestItemRepository;
        this.donorService = donorService;
    }

    @Override
    public DonationRequest createNewDonationRequest(CreateDonationDTO request) throws  Exception {
//        System.out.println(LocalDateTime.parse(request.getExpectedPickupTime() , DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")));
        if(donationRequestRepository.findByDonorIdAndIsDonationActiveTrue(request.getDonorId()) == null){
            DonationRequest dr = new DonationRequest();
            dr.setDonor(donorRepository.findById(request.getDonorId()).get());
            final RequestItem requestItemTemp = new RequestItem(
                    request.getCount(),
                    surplusMaterialRepository.findById(request.getSurplusMaterialId()).get()
            );
            donorService.findMostPoor(request.getCount(), dr.getDonor().getAddress().getCity());
            dr.setRecipientDonations(
                    recipientDonationRepository.findAllByDonationRequestId(dr.getId())
            );
            requestItemTemp.getSurplusMaterial().setRequestItems(null);
            requestItemRepository.save(requestItemTemp);
            dr.setDescription(request.getDescription());
            dr.setExpectedPickupTime(request.getExpectedPickupTime());
            dr.setLocation(request.getLocation());
            dr.setRequestItem(requestItemTemp);
            dr.setCreationDateAndTime(LocalDateTime.now());
            dr.setIsDonationActive(true);

            return donationRequestRepository.save(dr);
        }
        throw new Exception("Already exists an donation");
    }
    
    @Override
    public Optional<DonationRequest> getDonationRequestById(long id) {
        return donationRequestRepository.findById(id);
    }

    @Override
    public void updateVolunteerPickupByDonationRequest(EngagedDonationDTO engagedDonationDTO) throws NoSuchElementException, Exception  {
        Optional<DonationRequest> requestToBeUpdated = donationRequestRepository.findById(engagedDonationDTO.getRequestId());
        if(requestToBeUpdated.isPresent())      // Can throw self-made Exception here...
        {
            DonationRequest request = requestToBeUpdated.get();
            if (!request.getIsDonationActive()) return;    // The donation was closed by donor
            if (request.getVolunteerPickupTime() != null) return; // The donation isn't picked yet.

            request.setVolunteer(volunteerRepository.findById(engagedDonationDTO.getVolunteerId()).get());
            request.setVolunteerPickupTime(LocalDateTime.now());
            donationRequestRepository.save(request);
        } else {
            throw new Exception("Donation Not Found");
        }
    }

    @Override
    public void removeVolunteerPickupByDonationRequest(EngagedDonationDTO engagedDonationDTO) throws Exception {
        Optional<DonationRequest> requestToBeUpdated = donationRequestRepository.findById(engagedDonationDTO.getRequestId());
        if(requestToBeUpdated.isPresent())      // Can throw self-made Exception here...
        {
            DonationRequest request = requestToBeUpdated.get();
            if (!request.getIsDonationActive()) return;    // The donation was closed by donor
            if (request.getVolunteerPickupTime() == null) return; // The donation isn't picked yet.

            if(request.getVolunteer().getId() == engagedDonationDTO.getVolunteerId()){
                request.setVolunteer(null);
                request.setVolunteerPickupTime(null);
                donationRequestRepository.save(request);
            }
        } else {
            throw new Exception("Donation Not Found");
        }
    }

    @Override
    public void updateVolunteerEngagedTime(EngagedDonationDTO engagedDonationDTO) throws Exception  {
        Optional<DonationRequest> requestToBeUpdated = donationRequestRepository.findById(engagedDonationDTO.getRequestId());
        if(requestToBeUpdated.isPresent())      // Can throw self-made Exception here...
        {
            DonationRequest request = requestToBeUpdated.get();
            if(!request.getIsDonationActive()) return;    // The donation was closed by donor
            if(request.getVolunteerPickupTime() == null) return; // The donation isn't picked yet.
            if(request.getEngagedDateTime() != null) return;  // The donation already engaged by another one

            Donor donor = donorRepository.findById(request.getDonor().getId()).get();
            donor.setLastServed(LocalDateTime.now());
            donorRepository.save(donor);

            request.setEngagedDateTime(LocalDateTime.now());
            donationRequestRepository.save(request);
        } else {
            throw new Exception("Donation Not Found");
        }
    }
    
    @Override
    public void updateRecipientByDonationRequest(CompleteDonationDTO completeDonationDTO) throws Exception {

        Optional<DonationRequest> requestToBeUpdated = donationRequestRepository.findById(completeDonationDTO.getRequestId());
        if(requestToBeUpdated.isPresent())      // Can throw self-made Exception here...
        {
            DonationRequest request = requestToBeUpdated.get();     // Can throw self-made Exception here...
            if(!request.getIsDonationActive()) return;    // The donation was closed by donor
            if(request.getVolunteerPickupTime() == null) return; // The donation isn't picked yet.
            if(request.getEngagedDateTime() == null) return;  // The donation already engaged by another one
             // The donation had donated already
            if(request.getVolunteer().getId() != completeDonationDTO.getVolunteerId()) return; // The case another volunteer is trying to donate donation


            Volunteer volunteer = volunteerRepository.findById(completeDonationDTO.getVolunteerId()).get();
            volunteer.setLastServed(LocalDateTime.now());
            volunteerRepository.save(volunteer);
            Recipient recipient = recipientRepository.findById(completeDonationDTO.getRecipientId()).get();
            request.setRecipientDonations(request.getRecipientDonations().stream().map(e -> {
                if(e.getRecipient() == recipient){
                    e.setDonationDateTime(LocalDateTime.now());
                    recipientDonationRepository.save(e);
                }return e;}).toList());
            recipient.setLastServed(LocalDateTime.now());
            recipientRepository.save(recipient);
            boolean isAnyRecipientLeft = recipientDonationRepository.existsByDonationRequestIdAndDonationDateTimeIsNull(request.getId());
            request.setIsDonationActive(isAnyRecipientLeft);
            donationRequestRepository.save(request);
        } else {
            throw new Exception("Donation Not Found");
        }
    }
    
    @Override
    public List<DonationRequest> getAllSuccessfulDonationRequestByDonorId(Long donorId) {
	    return donationRequestRepository.findByDonorIdAndIsDonationActiveFalse(donorId);
    }
    // New Methods - Start
    @Override
    public DonationRequest getActiveDonationRequestByDonorId(Long donorId) throws Exception {
        return donationRequestRepository.findByDonorIdAndIsDonationActiveTrue(donorId);
    }

    @Override
    public List<DonationRequest> getAllSuccessfulDonationRequestByVolunteerId(Long volunteerId) {
        return donationRequestRepository.findByVolunteerIdAndIsDonationActiveFalse(volunteerId);
    }

    @Override
    public List<DonationRequest> getActiveDonationRequestByVolunteerId(Long volunteerId) {
        return null;
    }

    @Override
    public List<DonationRequest> getAllDonationRequestByRecipientId(Long recipientId) {
        return donationRequestRepository.findByRecipientDonationsRecipientIdAndIsDonationActiveFalse(recipientId);
    }

    @Override
    public List<DonationRequest> getAllActiveDonationRequestByRecipientId(Long recipientId) {
        return donationRequestRepository.findByRecipientDonationsRecipientIdAndIsDonationActiveTrueAndEngagedDateTimeNotNull(recipientId);
    }

    @Override
    public void closeActiveDonationById(Long id) throws Exception {
        DonationRequest activeDonation = getActiveDonationRequestByDonorId(id);
        activeDonation.setIsDonationActive(false);
        donationRequestRepository.save(activeDonation);
    }
    
    @Override
    public List<DonationRequest> getAllActiveDonation() {
        return donationRequestRepository.findByIsDonationActiveTrue();
    }
    // New Methods - End
}
