package com.Koupag.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDonationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    int numberOfDonationsNeeded;
   /* @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "list_Organization_Donation_Id", nullable = false)
    List<OrganizationDonation> listOrganizationDonationId;*/
    Long recipientOrganizationId;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_Item_Id", nullable = false)
    RequestItem requestItemId;
    int status;
    LocalDateTime creationDateAndTime;
    //LocalDateTime engagedDateAndTime;
    LocalDateTime successfulDonationDateAndTime;

}
