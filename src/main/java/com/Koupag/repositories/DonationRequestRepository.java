package com.Koupag.repositories;

import com.Koupag.models.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DonationRequestRepository extends JpaRepository<DonationRequest, UUID> {
    /*@Query("SELECT d FROM DonationRequest d WHERE d.donorId = :donorId")
    List<DonationRequest> findAllDonationRequestsByDonorId(@Param("donorId") Long donorId);*/

	// For Donor
	List<DonationRequest> findByDonorIdAndIsDonationActiveFalse(UUID id);
	DonationRequest findByDonorIdAndIsDonationActiveTrue(UUID id);

	// For Volunteer
	List<DonationRequest> findByVolunteerIdAndIsDonationActiveFalse(UUID id);

	// For Recipient
	List<DonationRequest> findByRecipientDonationsRecipientIdAndIsDonationActiveFalse(UUID id);

	List<DonationRequest> findByRecipientDonationsRecipientIdAndIsDonationActiveTrueAndEngagedDateTimeNotNull(UUID id);

	DonationRequest findByVolunteerIdAndIsDonationActiveTrueAndVolunteerPickupTimeNotNull(UUID id);

	// Donation Request
	List<DonationRequest> findByIsDonationActiveTrue();

	List<DonationRequest> findAllByDonorAddressCityAndIsDonationActiveTrueAndVolunteerPickupTimeNull(String city);
	
	
}
