package com.Koupag.dtos.donation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompleteDonationDTO {
	long requestId;
	long volunteerId;
	long recipientId;
}
