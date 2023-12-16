package com.Koupag.dtos.cities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class cityDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	Long Id;
	String name;
}
