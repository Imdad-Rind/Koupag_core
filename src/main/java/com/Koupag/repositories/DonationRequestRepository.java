package com.Koupag.repositories;

import com.Koupag.models.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRequestRepository extends JpaRepository<DonationRequest,Long> {
    /*@Query("SELECT d FROM DonationRequest d WHERE d.donorId = :donorId")
    List<DonationRequest> findAllDonationRequestsByDonorId(@Param("donorId") Long donorId);*/

	// For Donor
	List<DonationRequest> findByDonorIdAndIsDonationActiveFalse(long id);
	DonationRequest findByDonorIdAndIsDonationActiveTrue(long id);

	// For Volunteer
	List<DonationRequest> findByVolunteerIdAndIsDonationActiveFalse(long id);

	// For Recipient
	List<DonationRequest> findByRecipientsIdAndIsDonationActiveFalse(long id);

	List<DonationRequest> findByRecipientsIdAndIsDonationActiveTrueAndEngagedDateTimeNotNull(long id);

	// Donation Request
	List<DonationRequest> findByIsDonationActiveTrue();
	
	
}
