package com.Koupag.services;

public interface OTPService {
	String generateAndSendOtp(String email);
	
	String getOtp(String email);
	
	boolean verifyOtp(String email, String otp);

	void ExpireOTP(String otp);
}
