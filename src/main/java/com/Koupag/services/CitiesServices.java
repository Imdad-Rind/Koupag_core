package com.Koupag.services;

import com.Koupag.models.City;

import java.util.List;

public interface CitiesServices {
	
	void addNewCity(City city);
	List<City> getAllCities();
	City getCityById(Long id);
}
