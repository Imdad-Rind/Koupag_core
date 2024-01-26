package com.Koupag.services.services_implementations;

import com.Koupag.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Qualifier("gmail")
	private final JavaMailSender javaMailSender;
	
	@Autowired
	public EmailServiceImpl(final JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Override
	public void sendOTP(String email, String otp) throws MessagingException {
		SimpleMailMessage simpleOTPMail = new SimpleMailMessage();
		simpleOTPMail.setFrom(String.valueOf(new InternetAddress(" ")));
		simpleOTPMail.setTo(String.valueOf(new InternetAddress(email)));
		simpleOTPMail.setSubject("Koupag OTP Verification");
		simpleOTPMail.setText("Your OTP for Koupag is: " + otp + "\n\nnote this OTP will expire after 5 minutes");
		
		javaMailSender.send(simpleOTPMail);
	
	
	}
}
