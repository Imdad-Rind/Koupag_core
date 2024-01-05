package com.Koupag.repositories;

import com.Koupag.models.RecipientDonation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RecipientDonationRepository extends JpaRepository<RecipientDonation, UUID> {
    List<RecipientDonation> findAllByDonationRequestId(UUID id);
    boolean existsByDonationRequestIdAndDonationDateTimeIsNull(UUID id);
}
