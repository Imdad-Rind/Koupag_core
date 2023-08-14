package com.Koupag.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public abstract class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long Id;
    private String name;
    @Column(unique = true)
    private String registrationNumber;
}
