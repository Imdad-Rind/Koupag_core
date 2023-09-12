package com.Koupag.mappers;

import com.Koupag.dtos.address.addressDTO;
import com.Koupag.models.Address;
import org.springframework.stereotype.Component;

@Component
public class addressMapper{
	
	public addressDTO addressToDTO(Address address){
		addressDTO addressDTO = new addressDTO();
		addressDTO.setAreaName(address.getAreaName());
		addressDTO.setUcName(addressDTO.getUcName());
		addressDTO.setCityName(addressDTO.getCityName());
		return addressDTO;
	}
	
	public Address dtoToAddress(addressDTO addressDTO){
		Address address = new Address();
		address.setAreaName(addressDTO.getAreaName());
		address.setUcName(address.getUcName());
		address.setCityName(addressDTO.getCityName());
		
		return address;
	}
}
