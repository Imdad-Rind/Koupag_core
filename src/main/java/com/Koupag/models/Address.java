package com.Koupag.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    
    @JsonProperty("city")
    @JsonBackReference
    @ManyToOne(targetEntity = Cities.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "city")
    private Cities city;
    
    @JsonProperty("location")
    @JsonManagedReference
    @OneToOne(targetEntity = Location.class, fetch = FetchType.EAGER, mappedBy = "home_address",cascade = CascadeType.ALL)
    private Location location;
    
    @JsonManagedReference
    @OneToOne(targetEntity = UserProfile.class,fetch = FetchType.EAGER,mappedBy = "address")
    private UserProfile userProfile;



    public Address(String areaName, String ucName, Cities city, UserProfile users) {
        this.areaName = areaName;
        this.ucName = ucName;
        this.city = city;
        this.userProfile = users;
    }
    
    public Address(String areaName, String ucName, Cities city, Location location, UserProfile users) {
        this.areaName = areaName;
        this.ucName = ucName;
        this.city = city;
        this.location = location;
        this.userProfile = users;
    }
    
  
}
