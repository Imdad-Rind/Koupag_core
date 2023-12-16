package com.Koupag.dtos.donation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@JsonSerialize
public class CompleteDonationDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	long requestId;
	long volunteerId;
	long recipientId;
}
