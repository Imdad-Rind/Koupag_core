package com.Koupag.repositories;

import com.Koupag.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CitiesRepository extends JpaRepository<City, UUID> {
}
