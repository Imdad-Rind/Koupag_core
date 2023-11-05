package com.Koupag.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Donor extends User {
    @OneToMany(mappedBy = "donor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Transient
    DonationRequest request;

    @OneToOne(mappedBy = "donor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Transient
    OrganizationDonation donation;

}
