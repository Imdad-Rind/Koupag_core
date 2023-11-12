package com.Koupag.mappers;

import com.Koupag.dtos.cities.cityDTO;
import com.Koupag.models.City;
import org.springframework.stereotype.Component;

@Component
public class cityMapper {
	
	public cityDTO fromCityToDTO(City cities){
		cityDTO city = new cityDTO();
		city.setId(cities.getId());
		city.setName(cities.getName());
		return city;
	}
	public City fromDtoToCities(cityDTO dto){
		City city = new City();
		city.setId(dto.getId());
		city.setName(dto.getName());
		return city;
	}
}
