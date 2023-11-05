package com.Koupag.repositories;

import com.Koupag.models.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRequestRepository extends JpaRepository<DonationRequest,Long> {
    /*@Query("SELECT d FROM DonationRequest d WHERE d.donorId = :donorId")
    List<DonationRequest> findAllDonationRequestsByDonorId(@Param("donorId") Long donorId);*/
	
	List<DonationRequest> findDonationRequestsByDonorId(long id);
	List<DonationRequest> findDonationRequestsByVolunteerId(long id);
	List<DonationRequest> findDonationRequestsByRecipientId(long id);
	List<DonationRequest> findByIsDonationActiveTrue();
	
	
}
