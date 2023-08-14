package com.Koupag.services.services_implementations;

import com.Koupag.models.Organization;
import com.Koupag.repositories.OrganizationRepository;
import com.Koupag.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	final OrganizationRepository repository;
	@Autowired
	public OrganizationServiceImpl(OrganizationRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void createNewOrganization(Organization organization) {
		repository.save(organization);
	}
}
