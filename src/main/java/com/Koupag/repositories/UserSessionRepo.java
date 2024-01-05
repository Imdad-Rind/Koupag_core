package com.Koupag.repositories;

import com.Koupag.models.UserSessionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserSessionRepo extends JpaRepository<UserSessionModel, UUID> {
    @Query("SELECT fcmToken FROM UserSessionModel WHERE card = :card AND isActive = true")
    String findFcmTokensByCardAndIsActiveTrue(@Param("card") String card);
    UserSessionModel findFcmTokensByCard(String card);
}
