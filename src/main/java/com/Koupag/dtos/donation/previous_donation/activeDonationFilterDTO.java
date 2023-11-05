package com.Koupag.dtos.donation.previous_donation;

import com.Koupag.models.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
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
	@JsonProperty("pickup_time")
	String pickup_time;
	@JsonProperty("location")
	Location location;
	Boolean isDonationActive;
	LocalDateTime creationDateAndTime;
	LocalDateTime engagedDateAndTime;
	LocalDateTime successfulDonationDateAndTime;

}
