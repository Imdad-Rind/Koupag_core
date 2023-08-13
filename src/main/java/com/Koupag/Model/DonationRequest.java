package com.Koupag.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class DonationRequest {

    public DonationRequest(Donor donorId, Recipient recipientId, Volunteer volunteerId, RequestItem requestItemId, LocalDateTime creationDateAndTime, LocalDateTime engagedDateAndTime, LocalDateTime successfulDonationDateAndTime) {
        this.donorId = donorId;
        this.recipientId = recipientId;
        this.volunteerId = volunteerId;
        this.requestItemId = requestItemId;
        this.creationDateAndTime = creationDateAndTime;
        this.engagedDateAndTime = engagedDateAndTime;
        this.successfulDonationDateAndTime = successfulDonationDateAndTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donor_id", nullable = false)
    Donor donorId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_Id")
    Recipient recipientId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_Id")
    Volunteer volunteerId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_Item_Id", nullable = false)
    RequestItem requestItemId;
    LocalDateTime creationDateAndTime;
    LocalDateTime engagedDateAndTime;
    LocalDateTime successfulDonationDateAndTime;


}
