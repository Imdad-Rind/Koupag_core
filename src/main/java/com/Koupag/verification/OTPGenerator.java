package com.Koupag.verification;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;


@Component
public class OTPGenerator {

	private static final int OTP_LENGTH = 6;

	/*private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
	public  String generateComplexOTP(){
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= OTP_LENGTH; i++){
			int index = random.nextInt(ALLOWED_CHARACTERS.length());
			char character = ALLOWED_CHARACTERS.charAt(index);
			sb.append(character);
		}
		return sb.toString();
	}*/
	
	public String generateSimpleOTP(){
		String ALLOWED = "0123456789";
		
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= OTP_LENGTH; i++){
			int index = random.nextInt(ALLOWED.length());
			char character = ALLOWED.charAt(index);
			sb.append(character);
		}
		return sb.toString();
	}
}
