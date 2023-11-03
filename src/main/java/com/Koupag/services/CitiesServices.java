package com.Koupag.services;

import com.Koupag.models.Cities;

import java.util.List;

public interface CitiesServices {
	
	void addNewCity(Cities city);
	List<Cities> getAllCities();
	Cities getCityById(Long id);
}
