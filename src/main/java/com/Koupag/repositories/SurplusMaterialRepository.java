package com.Koupag.repositories;

import com.Koupag.models.SurplusMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SurplusMaterialRepository extends JpaRepository<SurplusMaterial, UUID> {
	SurplusMaterial findIdByName(String name);
}
