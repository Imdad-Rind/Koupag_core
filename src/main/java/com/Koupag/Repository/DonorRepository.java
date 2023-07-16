package com.Koupag.Repository;

import com.Koupag.Model.DonorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<DonorModel, Long> {
}
