package com.Koupag.services;

import com.Koupag.models.VerifiedUser;
import com.Koupag.repositories.VerifiedUserRepository;
import org.springframework.stereotype.Service;

@Service
public interface VerifiedUserService {
	
	void NewVerifiedUser(VerifiedUser verifiedUser);
	void verifyUserByEmail(String email);
	boolean isUserVerified(String email);
}
