package com.Koupag.repositories;

import com.Koupag.models.SurplusMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurplusMaterialRepository extends JpaRepository<SurplusMaterial, Long> {
	
	SurplusMaterial findIdByName(String name);
}
