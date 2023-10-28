package com.Koupag.dtos.user;

import com.Koupag.models.UserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class userProfileDTO {
	@JsonProperty("userProfile")
	UserProfile userProfile;
	@JsonProperty("userID")
	Long userID;
}
