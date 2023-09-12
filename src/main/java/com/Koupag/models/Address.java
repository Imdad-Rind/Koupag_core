package com.Koupag.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER,mappedBy = "address")
    private User user;
    
    public Address(String areaName, String ucName, String cityName) {
    }
}
