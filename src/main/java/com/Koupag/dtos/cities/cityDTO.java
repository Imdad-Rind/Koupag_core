package com.Koupag.dtos.cities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class cityDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	UUID Id;
	String name;
}
