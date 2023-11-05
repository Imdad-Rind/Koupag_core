package com.Koupag.mappers;

import com.Koupag.dtos.cities.cityDTO;
import com.Koupag.models.Cities;
import org.springframework.stereotype.Component;

@Component
public class cityMapper {
	
	public cityDTO fromCityToDTO(Cities cities){
		cityDTO city = new cityDTO();
		city.setId(cities.getId());
		city.setName(cities.getName());
		return city;
	}
	public Cities fromDtoToCities(cityDTO dto){
		Cities city = new Cities();
		city.setId(dto.getId());
		city.setName(dto.getName());
		return city;
	}
}
