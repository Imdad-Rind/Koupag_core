package com.Koupag.services.services_implementations;

import com.Koupag.models.Cities;
import com.Koupag.repositories.CitiesRepository;
import com.Koupag.services.CitiesServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesServicesImpl implements CitiesServices {
	
	private final CitiesRepository citiesRepository;
	
	public CitiesServicesImpl(CitiesRepository citiesRepository) {
		this.citiesRepository = citiesRepository;
	}
	
	@Override
	public void addNewCity(Cities city) {
		citiesRepository.save(city);
	}
	
	@Override
	public List<Cities> getAllCities() {
		return citiesRepository.findAll();
	}
	
	@Override
	public Cities getCityById(Long id) {
		return citiesRepository.findById(id).get();
	}
}
