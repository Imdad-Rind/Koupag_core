package com.Koupag.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
public abstract class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID Id;
    private String name;
    @Column(unique = true)
    private String registrationNumber;
}
