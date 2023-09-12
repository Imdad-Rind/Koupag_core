package com.Koupag.dtos.address;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class addressDTO  {
	private String areaName;
	private String ucName;
	private String cityName;
	//private User user;
	
	public addressDTO(String areaName, String ucName, String cityName) {
	}
}
