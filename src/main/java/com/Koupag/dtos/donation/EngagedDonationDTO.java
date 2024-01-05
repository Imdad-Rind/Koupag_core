package com.Koupag.dtos.donation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonSerialize
public class EngagedDonationDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	public EngagedDonationDTO(UUID requestId, UUID volunteerId) {
		this.requestId = requestId;
		this.volunteerId = volunteerId;
	}

	UUID requestId;
	UUID volunteerId;

//	public EngagedDonationDTO(String jsonString) {
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			EngagedDonationDTO engagedDonationDTO = objectMapper.readValue(jsonString, EngagedDonationDTO.class);
//			this.requestId = engagedDonationDTO.getRequestId();
//			this.volunteerId = engagedDonationDTO.getVolunteerId();
//
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//	}
}
