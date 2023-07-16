package com.Koupag.Repository;

import com.Koupag.Model.CashModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRepository extends JpaRepository<CashModel,Long> {
}
