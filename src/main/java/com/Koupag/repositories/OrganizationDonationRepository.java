package com.Koupag.repositories;

import com.Koupag.models.OrganizationDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationDonationRepository extends JpaRepository<OrganizationDonation, Long> {
}
