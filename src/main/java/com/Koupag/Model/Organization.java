package com.Koupag.Model;

import jakarta.persistence.*;



public abstract class Organization {
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id;
    private String name;
    private String registrationNumber;
}
