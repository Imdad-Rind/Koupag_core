package com.Koupag.dtos.verify;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@JsonSerialize
public class otpAndEmail implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@JsonProperty("email")
	String email;
	@JsonProperty("otp")
	String otp;
}
