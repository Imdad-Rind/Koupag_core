package com.Koupag.services;

import com.Koupag.models.City;

import java.util.List;
import java.util.UUID;

public interface CitiesServices {
	
	void addNewCity(City city);
	List<City> getAllCities();
	City getCityById(UUID id);
}
