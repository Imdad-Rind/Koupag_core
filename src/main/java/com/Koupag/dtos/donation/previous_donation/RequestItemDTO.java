package com.Koupag.dtos.donation.previous_donation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
@Getter
@Setter
public class RequestItemDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	int count;
	@JsonProperty
	SurplusMaterialDTO surplusMaterial;
}
