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
    private Long id;
    private String areaName;
    private String city;
    
    @JsonProperty("location")
    @JsonManagedReference
    @OneToOne(targetEntity = Location.class, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "location")
    private Location location;
    
    @JsonManagedReference
    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER,mappedBy = "address")
    private User user;

    
    public Address(String areaName, String city, Location location, User user) {
        this.areaName = areaName;
        this.city = city;
        this.location = location;
        this.user = user;
    }
    
  
}
