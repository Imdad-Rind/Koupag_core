package com.Koupag.services.services_implementations;

import com.Koupag.models.Address;
import com.Koupag.models.User;
import com.Koupag.repositories.AddressRepository;
import com.Koupag.repositories.UserRepository;
import com.Koupag.services.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
	private final AddressRepository addressRepository;
	private final UserRepository userRepository;
	
	public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public void addAddressByUserId(Long id, Address address) {
		
		Address addressToBeUpdated = addressRepository.findAddressByUserId(id);
		addressToBeUpdated.setAreaName(address.getAreaName());
		addressToBeUpdated.setUcName(address.getUcName());
		addressToBeUpdated.setCityName(address.getCityName());
		
		
	}
}
