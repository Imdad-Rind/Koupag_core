package com.Koupag.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@JsonProperty("latitude")
	private double latitude;
	@JsonProperty("longitude")
	private double longitude;
	
	@JsonBackReference
	@OneToOne(targetEntity = Address.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "location")
	@JoinColumn(name = "home_address")
	private Address home_address;
	
	@OneToOne(targetEntity = DonationRequest.class, mappedBy = "location",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private DonationRequest donationRequest;
	
	public Location(double latitude, double longitude, Address address) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.home_address = address;
	}
	
	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
