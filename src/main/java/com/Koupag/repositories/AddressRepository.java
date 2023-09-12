package com.Koupag.repositories;

import com.Koupag.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
	Address findAddressByUserId(Long id);
}
