package com.Koupag.mappers;

import com.Koupag.dtos.donation.previous_donation.*;
import com.Koupag.models.DonationRequest;
import com.Koupag.models.Location;
import com.Koupag.models.Recipient;
import com.Koupag.models.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DonationRequestMapper {
	
	private final Volunteer volunteer;
	private final Recipient recipient;
	@Autowired
	public DonationRequestMapper(Volunteer volunteer, Recipient recipient) {
		this.volunteer = volunteer;
		this.recipient = recipient;
	}
	
	public DonationRequestDTO fromDonationRequest(DonationRequest donationRequest) {
		DonationRequestDTO donationRequestDTOMapper = new DonationRequestDTO();
		donationRequestDTOMapper.setSuccessfulDonationDateAndTime(donationRequest.getSuccessfulDonationDateAndTime());
		donationRequestDTOMapper.setCreationDateAndTime(donationRequest.getCreationDateAndTime());
		donationRequestDTOMapper.setEngagedDateAndTime(donationRequest.getEngagedDateAndTime());
		donationRequestDTOMapper.setDescription(donationRequest.getDescription());
		donationRequestDTOMapper.setPickup_time(donationRequest.getPickup_time());
		donationRequestDTOMapper.setIsDonationActive(donationRequest.getIsDonationActive());
		donationRequestDTOMapper.setLocation(
				new Location(
						donationRequest.getLocation().getLatitude(),
						donationRequest.getLocation().getLongitude()
				)
		);
		donationRequestDTOMapper.setRequestItem(
				new RequestItemDTO(
						donationRequest.getRequestItem().getCount(),
						new SurplusMaterialDTO(
								donationRequest.getRequestItem().getSurplusMaterial().getName(),
								donationRequest.getRequestItem().getSurplusMaterial().getDescription()
						)
				)
		);
		donationRequestDTOMapper.setDonor(
				new DonorDTO(
//						donationRequest.getDonor().getName(),
						donationRequest.getDonor().getCNIC(),
						donationRequest.getDonor().getPhoneNumber(),
						donationRequest.getDonor().getEmail()
				)
		);
		donationRequestDTOMapper.setVolunteer(
				new VolunteerDTO(
//						donationRequest.getVolunteer().getName(),
						donationRequest.getVolunteer().getCNIC(),
						donationRequest.getVolunteer().getPhoneNumber(),
						donationRequest.getVolunteer().getEmail()
				)
		);
		donationRequestDTOMapper.setRecipient(
				new RecipientDTO(
//						donationRequest.getRecipient().getName(),
						donationRequest.getRecipient().getCNIC(),
						donationRequest.getRecipient().getPhoneNumber(),
						donationRequest.getRecipient().getEmail()
				)
		);
		return donationRequestDTOMapper;
	}
	
	
	public activeDonationFilterDTO fromActiveDonation(DonationRequest donationRequest) {
		activeDonationFilterDTO activeDonation = new activeDonationFilterDTO();
		activeDonation.setSuccessfulDonationDateAndTime(donationRequest.getSuccessfulDonationDateAndTime());
		activeDonation.setCreationDateAndTime(donationRequest.getCreationDateAndTime());
		activeDonation.setEngagedDateAndTime(donationRequest.getEngagedDateAndTime());
		activeDonation.setDescription(donationRequest.getDescription());
		activeDonation.setPickup_time(donationRequest.getPickup_time());
		activeDonation.setIsDonationActive(donationRequest.getIsDonationActive());
		activeDonation.setLocation(
				new Location(
						donationRequest.getLocation().getLatitude(),
						donationRequest.getLocation().getLongitude()
				)
		);
		activeDonation.setRequestItem(
				new RequestItemDTO(
						donationRequest.getRequestItem().getCount(),
						new SurplusMaterialDTO(
								donationRequest.getRequestItem().getSurplusMaterial().getName(),
								donationRequest.getRequestItem().getSurplusMaterial().getDescription()
						)
				)
		);
		activeDonation.setDonor(
				new DonorDTO(
//						donationRequest.getDonor().getName(),
						donationRequest.getDonor().getCNIC(),
						donationRequest.getDonor().getPhoneNumber(),
						donationRequest.getDonor().getEmail()
				)
		);
		if (volunteer.getCNIC() != null) {
			activeDonation.setVolunteer(
					new VolunteerDTO(
//							donationRequest.getVolunteer().getName(),
							donationRequest.getVolunteer().getCNIC(),
							donationRequest.getVolunteer().getPhoneNumber(),
							donationRequest.getVolunteer().getEmail()
					)
			);
		} else {
			activeDonation.setVolunteer(null); // or set to a default VolunteerDTO object
		}
		if (recipient.getCNIC() != null) {
			activeDonation.setVolunteer(
					new VolunteerDTO(
//					donationRequest.getRecipient().getName(),
					donationRequest.getRecipient().getCNIC(),
					donationRequest.getRecipient().getPhoneNumber(),
					donationRequest.getRecipient().getEmail()
					)
			);
		} else {
			activeDonation.setRecipient(null);
		}

		return activeDonation;
	}
	
	
	
}
