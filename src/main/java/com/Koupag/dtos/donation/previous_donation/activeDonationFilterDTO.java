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
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class activeDonationFilterDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	DonorDTO donor;
	RecipientDTO recipient = null;
	VolunteerDTO volunteer = null;
	@JsonProperty
	RequestItemDTO requestItem;
	@JsonProperty("description")
	String description;
	@JsonProperty("location")
	Location location;
	Boolean isDonationActive;
	LocalDateTime creationDateAndTime;                  // The time a Donation Request is created
	LocalDateTime expectedPickupTime;					// The time a Donor fix to be picked
	LocalDateTime volunteerPickupTime;                  // The time a Volunteer announces to pick that donation
	LocalDateTime engagedDateAndTime;                   // The time a Volunteer Actually pickup the donation
	LocalDateTime successfulDonationDateAndTime;        // The time a Volunteer hand over the donation

}
