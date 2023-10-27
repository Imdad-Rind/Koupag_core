package com.Koupag.services.services_implementations;

import com.Koupag.services.OTPService;
import com.Koupag.verification.OTPGenerator;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
@Service
public class OTPServiceImpl implements OTPService {
	
	private final OTPGenerator otpGenerator;
	private final LoadingCache<String,String> otpCache;
	@Autowired
	public OTPServiceImpl(OTPGenerator otpGenerator) {
		this.otpGenerator = otpGenerator;
		this.otpCache = CacheBuilder.newBuilder()
				                .expireAfterWrite(5, TimeUnit.MINUTES)
				                .build(new CacheLoader<String, String>() {
					                @Override
					                public String load(String email) throws Exception {
						                return otpGenerator.generateSimpleOTP();
					                }
				                });
	}
	
	@Override
	public String generateAndSendOtp(String email) {
		String newOTP = otpGenerator.generateSimpleOTP();
		otpCache.put(email,newOTP);
		return newOTP;
	}
	
	@Override
	public String getOtp(String email) {
		return otpCache.getUnchecked(email);
	}
	
	@Override
	public boolean verifyOtp(String email, String otp) {
		return Objects.equals(otpCache.getIfPresent(email), otp);
	}
}
