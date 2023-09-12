package com.Koupag.dtos.register;

import com.Koupag.models.Address;

//{
//		"name" : "D",
//		"cnic" : "123",
//		"phoneNumber" : "123",
//		"emailAddress" : "D23@D.com",
//		"username" : "D23",
//		"password" : "D",
//		"userType" : "DONOR",
//		"address" :
//		{
//		"areaName" : "areaName",
//		"UCName" : "UCName",
//		"cityName" : "cityName"
//		}
//
//		}
public record RegisterRequest(String name, String cnic, String phoneNumber, String emailAddress, String username, String password, String userType, Address address) {
}
