package com.Koupag.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cash extends RequestItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    Double amount;
}
