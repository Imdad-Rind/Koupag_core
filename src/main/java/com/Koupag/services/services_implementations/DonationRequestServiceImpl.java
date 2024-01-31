package com.Koupag.services.services_implementations;

import com.Koupag.AvailableNotifications;
import com.Koupag.dtos.donation.CreateDonationDTO;
import com.Koupag.models.*;
import com.Koupag.dtos.donation.EngagedDonationDTO;
import com.Koupag.dtos.donation.CompleteDonationDTO;
import com.Koupag.repositories.*;
import com.Koupag.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
    private final FCMService fcmService;
    private final UserSessionService userSessionService;
    private final NotifyService notifyService;
    @Autowired
    public DonationRequestServiceImpl(DonationRequestRepository donationRequestRepository,
                                      RecipientDonationRepository recipientDonationRepository, DonorRepository donorRepository, VolunteerRepository volunteerRepository,
                                      RecipientRepository recipientRepository, SurplusMaterialRepository surplusMaterialRepository,
                                      RequestItemRepository requestItemRepository, DonorService donorService, FCMService fcmService, UserSessionService userSessionService, NotifyService notifyService) {
        this.donationRequestRepository = donationRequestRepository;
        this.recipientDonationRepository = recipientDonationRepository;
        this.donorRepository = donorRepository;
        this.volunteerRepository = volunteerRepository;
        this.recipientRepository = recipientRepository;
        this.surplusMaterialRepository = surplusMaterialRepository;
        this.requestItemRepository = requestItemRepository;
        this.donorService = donorService;
        this.fcmService = fcmService;
        this.userSessionService = userSessionService;
        this.notifyService = notifyService;
    }

    @Override
    public DonationRequest getRespondedDonationOfVolunteer(UUID id) {
        return donationRequestRepository.findByVolunteerIdAndIsDonationActiveTrueAndVolunteerPickupTimeNotNull(id);
    }

    @Override
    public void createNewDonationRequest(CreateDonationDTO request) throws  Exception {
//        System.out.println(LocalDateTime.parse(request.getExpectedPickupTime() , DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")));
        if(donationRequestRepository.findByDonorIdAndIsDonationActiveTrue(request.getDonorId()) == null){
            DonationRequest dr = new DonationRequest();
            final Donor donor = donorRepository.findById(request.getDonorId()).get();
            dr.setDonor(donor);
            final RequestItem requestItemTemp = new RequestItem(
                    request.getCount(),
                    surplusMaterialRepository.findById(request.getSurplusMaterialId()).get()
            );
            donorService.findMostPoor(request.getCount(), dr.getDonor().getAddress().getCity());
            dr.setRecipientDonations(
                    recipientDonationRepository.findAllByDonationRequestId(dr.getId())
            );
            requestItemTemp.getSurplusMaterial().setRequestItemList(null);
            requestItemRepository.save(requestItemTemp);
            dr.setDescription(request.getDescription());
            dr.setExpectedPickupTime(request.getExpectedPickupTime());
            dr.setLocation(request.getLocation());
            dr.setItem(requestItemTemp);
            dr.setCreationDateAndTime(LocalDateTime.now());
            dr.setIsDonationActive(true);
            // the nearest volunteer will be determined here
            // for now just getting nearest as based on same city

            // Sending Notifications
            List<Volunteer> nearestVolunteers = volunteerRepository.findByAddressCity(donor.getAddress().getCity());
            notifyService.donationCreationNotification(
                    nearestVolunteers,
                    AvailableNotifications.notifyDonationCreationToVolunteer(dr)
            );
            donationRequestRepository.save(dr);

            return;
        }
        throw new Exception("Already exists an donation");
    }
    
    @Override
    public Optional<DonationRequest> getDonationRequestById(UUID id) {
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
            Volunteer volunteer = volunteerRepository.findById(engagedDonationDTO.getVolunteerId()).get();
            request.setVolunteer(volunteer);
            request.setVolunteerPickupTime(LocalDateTime.now());
            notifyService.pickupNotification(request.getDonor(), AvailableNotifications.notifyPickupToDonor(request));
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
                notifyService.UnpickNotification(request.getDonor(), AvailableNotifications.notifyUnpickToDonor(request));
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

            List<Recipient> recipientList = request.getRecipientDonations().stream().map(RecipientDonation::getRecipient).toList();
            notifyService.engageNotification(recipientList, AvailableNotifications.notifyDonationCreationToVolunteer(request));
            donationRequestRepository.save(request);
        } else {
            throw new Exception("Donation Not Found");
        }
    }
    
    @Override
    public void updateRecipientByDonationRequest(CompleteDonationDTO completeDonationDTO) throws NoSuchElementException, Exception {

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
//            Recipient recipient = recipientRepository.findById(completeDonationDTO.getRecipientId()).get();
//            recipient.setLastServed(LocalDateTime.now());
//            recipientRepository.save(recipient);
            Optional<Recipient> recipient = Optional.empty();
            for(RecipientDonation rd: request.getRecipientDonations()){
                if(rd.getRecipient().getId() == completeDonationDTO.getRecipientId()){
                    recipient = Optional.of(rd.getRecipient());
                    rd.setDonationDateTime(LocalDateTime.now());
                    rd.getRecipient().setLastServed(LocalDateTime.now());
                    recipientRepository.save(rd.getRecipient());
                    recipientDonationRepository.save(rd);
                }
            }
            boolean isAnyRecipientLeft = recipientDonationRepository.existsByDonationRequestIdAndDonationDateTimeIsNull(request.getId());
            request.setIsDonationActive(isAnyRecipientLeft);
            notifyService.completionNotification(
                    request.getDonor(),
                    AvailableNotifications.notifySuccessToDonor(request)
            );
            donationRequestRepository.save(request);
        } else {
            throw new Exception("Donation Not Found");
        }
    }
    
    @Override
    public List<DonationRequest> getAllSuccessfulDonationRequestByDonorId(UUID donorId) {
	    return donationRequestRepository.findByDonorIdAndIsDonationActiveFalse(donorId);
    }
    // New Methods - Start
    @Override
    public DonationRequest getActiveDonationRequestByDonorId(UUID donorId) throws Exception {
        return donationRequestRepository.findByDonorIdAndIsDonationActiveTrue(donorId);
    }

    @Override
    public List<DonationRequest> getAllSuccessfulDonationRequestByVolunteerId(UUID volunteerId) {
        return donationRequestRepository.findByVolunteerIdAndIsDonationActiveFalse(volunteerId);
    }

    @Override
    public List<DonationRequest> getActiveDonationRequestByVolunteerId(UUID volunteerId) {
        return null;
    }

    @Override
    public List<DonationRequest> getAllDonationRequestByRecipientId(UUID recipientId) {
        return donationRequestRepository.findByRecipientDonationsRecipientIdAndIsDonationActiveFalse(recipientId);
    }

    @Override
    public List<DonationRequest> getAllActiveDonationRequestByRecipientId(UUID recipientId) {
        return donationRequestRepository.findByRecipientDonationsRecipientIdAndIsDonationActiveTrueAndEngagedDateTimeNotNull(recipientId);
    }

    @Override
    public void closeActiveDonationById(UUID id) throws Exception {
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
