package com.Koupag.Repository;

import com.Koupag.Model.RecipientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientRepository extends JpaRepository<RecipientModel, Long> {
}
