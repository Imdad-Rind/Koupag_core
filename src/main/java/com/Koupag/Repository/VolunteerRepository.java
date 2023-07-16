package com.Koupag.Repository;

import com.Koupag.Model.VolunteerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<VolunteerModel, Long> {
}
