package com.Koupag.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donor_id", nullable = false)
    Donor donorId;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "volunteer_Id", nullable = false)
    Volunteer volunteerId;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_Item_Id", nullable = false)
    RequestItem requestItemId;
    LocalDateTime creationDateAndTime;
    LocalDateTime engagedDateAndTime;
    LocalDateTime successfulDonationDateAndTime;

  /*  @OneToOne(mappedBy = "listOrganizationDonationId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    OrganizationDonationRequest organizationDonationRequest;*/
}
