package com.Koupag.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Donor extends User {
    @OneToOne(mappedBy = "donor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Transient
    DonationRequest request;

    @OneToOne(mappedBy = "donor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Transient
    OrganizationDonation donation;

}
