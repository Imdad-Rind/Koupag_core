package com.Koupag.dtos.verify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class otpAndEmail {
	@JsonProperty("email")
	String email;
	@JsonProperty("otp")
	String otp;
}
