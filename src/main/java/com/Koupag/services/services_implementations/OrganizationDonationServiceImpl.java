package com.Koupag.services.services_implementations;

import com.Koupag.models.OrganizationDonation;
import com.Koupag.repositories.OrganizationDonationRepository;
import com.Koupag.services.OrganizationDonationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationDonationServiceImpl implements OrganizationDonationServices {
	
	private final OrganizationDonationRepository repository;
	
	@Autowired
	public OrganizationDonationServiceImpl(OrganizationDonationRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void createNewOrganizationDonation(OrganizationDonation organizationDonation) {
		repository.save(organizationDonation);
	}
}
