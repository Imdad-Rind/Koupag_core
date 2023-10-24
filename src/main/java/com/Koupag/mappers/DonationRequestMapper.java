package com.Koupag.mappers;

import com.Koupag.dtos.donation.previous_donation.*;
import com.Koupag.models.DonationRequest;
import com.Koupag.models.Location;
import org.springframework.stereotype.Component;

@Component
public class DonationRequestMapper {
	 public DonationRequestDTO fromDonationRequest(DonationRequest donationRequest) {
		DonationRequestDTO donationRequestDTOMapper = new DonationRequestDTO();
		donationRequestDTOMapper.setSuccessfulDonationDateAndTime(donationRequest.getSuccessfulDonationDateAndTime());
		donationRequestDTOMapper.setCreationDateAndTime(donationRequest.getCreationDateAndTime());
		donationRequestDTOMapper.setEngagedDateAndTime(donationRequest.getEngagedDateAndTime());
		/*donationRequestDTOMapper.setDescription(donationRequest.getDescription());
		donationRequestDTOMapper.setPickup_time(donationRequest.getPickup_time());
		donationRequestDTOMapper.setLocation(
				new Location(
						donationRequest.getLocation().getLatitude(),
						donationRequest.getLocation().getLongitude()
				)
		);*/
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
						donationRequest.getDonor().getName(),
						donationRequest.getDonor().getCNIC(),
						donationRequest.getDonor().getPhoneNumber(),
						donationRequest.getDonor().getEmailAddress()
				)
		);
		donationRequestDTOMapper.setVolunteer(
				new VolunteerDTO(
						donationRequest.getVolunteer().getName(),
						donationRequest.getVolunteer().getCNIC(),
						donationRequest.getVolunteer().getPhoneNumber(),
						donationRequest.getVolunteer().getEmailAddress()
				)
		);
		donationRequestDTOMapper.setRecipient(
				new RecipientDTO(
						donationRequest.getRecipient().getName(),
						donationRequest.getRecipient().getCNIC(),
						donationRequest.getRecipient().getPhoneNumber(),
						donationRequest.getRecipient().getEmailAddress()
				)
		);
		return donationRequestDTOMapper;
	}
	
	
	
}
