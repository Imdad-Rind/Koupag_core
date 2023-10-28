package com.Koupag.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String areaName;
    private String ucName;
    private String cityName;
    
    @JsonProperty("location")
    @JsonManagedReference
    @OneToOne(targetEntity = Location.class, fetch = FetchType.EAGER, mappedBy = "home_address",cascade = CascadeType.ALL)
    private Location location;
    @JsonManagedReference
    @OneToOne(targetEntity = UserProfile.class,fetch = FetchType.EAGER,mappedBy = "address")
    private UserProfile userProfile;



    public Address(String areaName, String ucName, String cityName, UserProfile users) {
        this.areaName = areaName;
        this.ucName = ucName;
        this.cityName = cityName;
        this.userProfile = users;
    }
    
    public Address(String areaName, String ucName, String cityName, Location location, UserProfile users) {
        this.areaName = areaName;
        this.ucName = ucName;
        this.cityName = cityName;
        this.location = location;
        this.userProfile = users;
    }
    
  
}
