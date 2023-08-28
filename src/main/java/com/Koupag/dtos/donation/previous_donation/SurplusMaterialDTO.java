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
@JsonSerialize
@Setter
@Getter
public class SurplusMaterialDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	String name;
	String description;
}
