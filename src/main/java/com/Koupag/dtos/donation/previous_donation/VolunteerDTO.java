package com.Koupag.dtos.donation.previous_donation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonSerialize
public class VolunteerDTO implements Serializable {
	//String name;
	@Serial
	private static final long serialVersionUID = 1L;
	String cnic;
	String phoneNumber;
	String emailAddress;
}
