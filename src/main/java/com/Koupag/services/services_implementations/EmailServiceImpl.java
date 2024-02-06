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
		simpleOTPMail.setFrom(String.valueOf(new InternetAddress("Koupag_Org")));
		simpleOTPMail.setTo(String.valueOf(new InternetAddress(email)));
		simpleOTPMail.setSubject("Koupag OTP Verification");
		simpleOTPMail.setText("Subject: Your Koupag OTP\n" +
				"\n" +
				"Dear User,\n" +
				"\n" +
				"This email contains your one-time password (OTP) to verify your account. Please use this code within the next 5 minutes for it to be valid.\n" +
				"\n" +
				"Your OTP is: "+otp+"\n" +
				"\n" +
				"Please do not share this OTP with anyone.\n" +
				"\n" +
				"If you did not request an OTP, please disregard this email.\n" +
				"\n" +
				"Sincerely,\n" +
				"\n" +
				"The Koupag Team");
		
		javaMailSender.send(simpleOTPMail);
	
	
	}
}
