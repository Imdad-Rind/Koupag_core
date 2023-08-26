package com.Koupag.mappers;

import com.Koupag.dtos.donation.previous_donation.*;
import com.Koupag.models.DonationRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Setter
@Getter
@Component
public class DonationRequestMapper {
	LocalDateTime createdAt;
	LocalDateTime engagedAt;
	LocalDateTime donatedAt;
	DonorDTO donor;
	RecipientDTO recipient;
	VolunteerDTO volunteer;
	RequestItemDTO requestItem;
	
	 public DonationRequestDTO fromDonationRequest(DonationRequest donationRequest) {
		DonationRequestDTO donationRequestDTOMapper = new DonationRequestDTO();
		donationRequestDTOMapper.setSuccessfulDonationDateAndTime(donationRequest.getSuccessfulDonationDateAndTime());
		donationRequestDTOMapper.setCreationDateAndTime(donationRequest.getCreationDateAndTime());
		donationRequestDTOMapper.setEngagedDateAndTime(donationRequest.getEngagedDateAndTime());
		donationRequestDTOMapper.setRequestItem(
				new RequestItemDTO(
						donationRequest.getRequestItemId().getCount(),
						new SurplusMaterialDTO(
								donationRequest.getRequestItemId().getSurplusMaterial().getName(),
								donationRequest.getRequestItemId().getSurplusMaterial().getDescription()
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
	
	/*public RequestItemDTO toRequestItemDTO (RequestItemDTO requestItem){
		 RequestItemDTO requestItemDTO = new RequestItemDTO();
		 
		 return null;
		 
		 
	}*/
	
	
}
