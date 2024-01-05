package com.Koupag.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public  class RequestItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID Id;
    
    public RequestItem(int count, SurplusMaterial surplusMaterial) {
        this.count = count;
        this.surplusMaterial = surplusMaterial;
    }
    
    int count;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surplus_Material")
    SurplusMaterial surplusMaterial;
    
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    DonationRequest request;
    @OneToOne(mappedBy = "requestItemId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    OrganizationDonation donation;

    @OneToOne(mappedBy = "requestItemId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    OrganizationDonationRequest organizationDonationRequest;
    
}
