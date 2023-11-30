package com.Koupag.repositories;

import com.Koupag.models.RecipientDonation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipientDonationRepository extends JpaRepository<RecipientDonation, Long> {
    List<RecipientDonation> findAllByDonationRequestId(Long id);
    boolean existsByDonationRequestIdAndDonationDateTimeIsNull(Long id);
}
