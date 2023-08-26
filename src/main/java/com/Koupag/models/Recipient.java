package com.Koupag.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipient extends User {
    @OneToOne(mappedBy = "recipient", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Transient
    DonationRequest request;
    
   
}
