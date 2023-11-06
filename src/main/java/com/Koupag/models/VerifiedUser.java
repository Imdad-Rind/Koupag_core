package com.Koupag.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerifiedUser {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	UUID Id;
	@JsonProperty("cnic")
	@Column(unique = true)
	String CNIC;
	@JsonProperty("email")
	@Column(unique = true)
	String email;
	@JsonProperty("phone")
	@Column(unique = true)
	private String phoneNumber;
	Boolean isUserVerified = false;
	LocalDateTime VerificationTime;
	
	public VerifiedUser(String CNIC, String email, Boolean isUserVerified, LocalDateTime verificationTime) {
		this.CNIC = CNIC;
		this.email = email;
		this.isUserVerified = isUserVerified;
		VerificationTime = verificationTime;
	}
}
