package com.Koupag.repositories;

import com.Koupag.models.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRequestRepository extends JpaRepository<DonationRequest,Long> {
    //List<DonationRequest> findAllByRecipientId(long recipientId);
}
