package com.Koupag.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDonationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID Id;
    int numberOfDonationsNeeded;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_Item_Id", nullable = false)
    RequestItem requestItemId;
    int status;
    LocalDateTime creationDateAndTime;
    LocalDateTime successfulDonationDateAndTime;

}
