package com.Koupag.Model;

import jakarta.persistence.*;

@Entity

public  class RequestItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    String count;
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
