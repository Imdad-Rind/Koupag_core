package com.Koupag.repositories;

import com.Koupag.models.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRepository extends JpaRepository<Cities, Long> {
}
