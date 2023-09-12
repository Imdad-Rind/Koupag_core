package com.Koupag.services;

import com.Koupag.models.Address;

public interface AddressService {
	
	void addAddressByUserId(Long id, Address address);
}
