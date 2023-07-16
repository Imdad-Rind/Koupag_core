package com.Koupag.Repository;

import com.Koupag.Model.SurplusMaterialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurplusMaterialRepository extends JpaRepository<SurplusMaterialModel, Long> {
}
