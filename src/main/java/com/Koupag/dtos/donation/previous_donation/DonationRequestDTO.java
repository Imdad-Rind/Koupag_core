package com.Koupag.dtos.donation.previous_donation;

import com.Koupag.models.Donor;
import com.Koupag.models.Recipient;
import com.Koupag.models.RequestItem;
import com.Koupag.models.Volunteer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class DonationRequestDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	DonorDTO donor;
	RecipientDTO recipient;
	VolunteerDTO volunteer;
	@JsonProperty
	RequestItemDTO requestItem;
	LocalDateTime creationDateAndTime;
	LocalDateTime engagedDateAndTime;
	LocalDateTime successfulDonationDateAndTime;
	
}
