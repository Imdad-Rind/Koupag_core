package com.Koupag.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Var;

import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public  class RequestItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    
    public RequestItem(int count, SurplusMaterial surplusMaterial) {
        this.count = count;
        this.surplusMaterial = surplusMaterial;
    }
    
    int count;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surplus_Material")
    SurplusMaterial surplusMaterial;
    
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
