package com.Koupag.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RecipientDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    LocalDateTime donationDateTime;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    Recipient recipient;
    @ManyToOne(fetch = FetchType.LAZY)
    DonationRequest donationRequest;


    public RecipientDonation(Recipient recipient){
        this.recipient = recipient;
    }
}
