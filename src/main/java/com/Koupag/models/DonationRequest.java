package com.Koupag.models;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor

public class DonationRequest{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donor_id", nullable = false)
    Donor donor;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_Id")
    List<Recipient> recipients;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_Id")
    Volunteer volunteer;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_Item_Id", nullable = true)
    RequestItem requestItem;
    
    String description;
    Boolean isDonationActive;
    
    @OneToOne(targetEntity = Location.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "pickup_location")
    Location location;

    LocalDateTime creationDateAndTime;                  // The time a Donation Request is created
    String expectedPickupTime;                   // The time a Donor fix to be picked
    LocalDateTime volunteerPickupTime;                  // The time a Volunteer announces to pick that donation
    LocalDateTime engagedDateTime;                   // The time a Volunteer Actually pickup the donation
    LocalDateTime successfulDonationDateAndTime;        // The time a Volunteer hand over the donation

    public DonationRequest(Donor donor, List<Recipient> recipients, Volunteer volunteer, RequestItem requestItem, String description, Boolean isDonationActive, Location location, LocalDateTime creationDateAndTime, String expectedPickupTime, LocalDateTime volunteerPickupTime, LocalDateTime engagedDateTime, LocalDateTime successfulDonationDateAndTime) {
        this.donor = donor;
        this.recipients = recipients;
        this.volunteer = volunteer;
        this.requestItem = requestItem;
        this.description = description;
        this.isDonationActive = isDonationActive;
        this.location = location;
        this.creationDateAndTime = creationDateAndTime;
        this.expectedPickupTime = expectedPickupTime;
        this.volunteerPickupTime = volunteerPickupTime;
        this.engagedDateTime = engagedDateTime;
        this.successfulDonationDateAndTime = successfulDonationDateAndTime;
    }
}
