package com.Koupag.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public  class RequestItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    int count;
    String description;
    @OneToOne(mappedBy = "requestItemId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    DonationRequest request;
    @OneToOne(mappedBy = "requestItemId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    OrganizationDonation donation;

    @OneToOne(mappedBy = "requestItemId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    OrganizationDonationRequest organizationDonationRequest;
}
