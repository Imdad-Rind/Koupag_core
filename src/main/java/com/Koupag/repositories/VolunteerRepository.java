package com.Koupag.repositories;

import com.Koupag.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    List<Volunteer> findByAddressCity(String city);
}
