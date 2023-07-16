package com.Koupag.Repository;

import com.Koupag.Model.OtherSurplusItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherSurplusItemRepository extends JpaRepository<OtherSurplusItemModel, Long> {
}
