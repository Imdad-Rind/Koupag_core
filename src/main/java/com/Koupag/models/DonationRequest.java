package com.Koupag.models;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class DonationRequest{

    public DonationRequest(Donor donor, Recipient recipient, Volunteer volunteer, RequestItem requestItem, LocalDateTime creationDateAndTime, LocalDateTime engagedDateAndTime, LocalDateTime successfulDonationDateAndTime) {
        this.donor = donor;
        this.recipient = recipient;
        this.volunteer = volunteer;
        this.requestItem = requestItem;
        this.creationDateAndTime = creationDateAndTime;
        this.engagedDateAndTime = engagedDateAndTime;
        this.successfulDonationDateAndTime = successfulDonationDateAndTime;
    }
  
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donor_id", nullable = false)
    Donor donor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_Id")
    Recipient recipient;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_Id")
    Volunteer volunteer;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_Item_Id", nullable = true)
    RequestItem requestItem;
    LocalDateTime creationDateAndTime;
    LocalDateTime engagedDateAndTime;
    LocalDateTime successfulDonationDateAndTime;


}
