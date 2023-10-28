package com.Koupag.services;

import jakarta.mail.MessagingException;

public interface EmailService {

	public void sendOTP(String email, String otp) throws MessagingException;
}
