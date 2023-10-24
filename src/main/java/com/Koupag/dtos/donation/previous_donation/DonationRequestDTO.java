package com.Koupag.dtos.donation.previous_donation;

import com.Koupag.models.*;
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
	@JsonProperty("description")
	String description;
	@JsonProperty("pickup_time")
	String pickup_time;
	@JsonProperty("location")
	Location location;
	LocalDateTime creationDateAndTime;
	LocalDateTime engagedDateAndTime;
	LocalDateTime successfulDonationDateAndTime;
	
}
