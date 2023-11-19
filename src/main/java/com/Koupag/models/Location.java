package com.Koupag.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonProperty("latitude")
	private String latitude;
	@JsonProperty("longitude")
	private String longitude;
	
	@JsonBackReference
	@OneToOne(targetEntity = Address.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "location")
//	@JoinColumn(referencedColumnName = "LOCATION_ID")
	@JoinColumn(name = "home_address")
	private Address home_address;
	
	@OneToOne(targetEntity = DonationRequest.class, mappedBy = "location",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private DonationRequest donationRequest;
	
	public Location(String latitude, String longitude, Address address) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.home_address = address;
	}
	
	public Location(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
