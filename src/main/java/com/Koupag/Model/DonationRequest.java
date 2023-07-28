package com.Koupag.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donor_id", nullable = false)
    Donor donorId;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipient_Id", nullable = false)
    Recipient recipientId;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "volunteer_Id", nullable = false)
    Volunteer volunteerId;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_Item_Id", nullable = false)
    RequestItem requestItemId;
    int status;
    LocalDateTime creationDateAndTime;
    LocalDateTime engagedDateAndTime;
    LocalDateTime successfulDonationDateAndTime;


}
