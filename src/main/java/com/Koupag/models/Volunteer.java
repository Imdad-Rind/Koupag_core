package com.Koupag.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Volunteer extends User {
    @OneToMany(mappedBy = "volunteer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Transient
    DonationRequest request;

    @OneToOne(mappedBy = "volunteer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Transient
    OrganizationDonation donation;
}
