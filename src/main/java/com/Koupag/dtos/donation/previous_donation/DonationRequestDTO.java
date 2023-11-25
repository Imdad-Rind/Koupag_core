package com.Koupag.dtos.donation.previous_donation;

import com.Koupag.models.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
	Boolean isDonationActive;
	@JsonProperty("description")
	String description;
	String expectedPickupTime;
	@JsonProperty("location")
	Location location;
	LocalDateTime volunteerPickupTime;
	LocalDateTime creationDateAndTime;
	LocalDateTime engagedDateAndTime;
	LocalDateTime successfulDonationDateAndTime;
	
}
