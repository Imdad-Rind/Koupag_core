package com.Koupag.services.services_implementations;

import com.Koupag.models.VerifiedUser;
import com.Koupag.repositories.VerifiedUserRepository;
import com.Koupag.services.VerifiedUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerifiedUserServiceImpl implements VerifiedUserService {
	
	private final VerifiedUserRepository verifiedUserRepo;
	
	public VerifiedUserServiceImpl(VerifiedUserRepository verifiedUserRepo) {
		this.verifiedUserRepo = verifiedUserRepo;
	}
	
	@Override
	public void NewVerifiedUser(VerifiedUser verifiedUser) {
		verifiedUserRepo.save(verifiedUser);
	}
	
	@Override
	public void verifyUserByEmail(String email) {
		VerifiedUser user = verifiedUserRepo.findByEmail(email);
		user.setIsUserVerified(true);
		user.setVerificationTime(LocalDateTime.now());
		verifiedUserRepo.save(user);
		
	}
	
	@Override
	public boolean isUserVerified(String email) {
		VerifiedUser verifiedUser = verifiedUserRepo.findByEmail(email);
		return verifiedUser.getIsUserVerified();
	}
	
	
}
