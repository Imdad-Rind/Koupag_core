package com.Koupag.repositories;

import com.Koupag.models.VerifiedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VerifiedUserRepository extends JpaRepository<VerifiedUser, UUID> {
	
	VerifiedUser findByEmail(String email);
}
