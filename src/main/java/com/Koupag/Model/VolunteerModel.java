package com.Koupag.Model;

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
public class VolunteerModel extends UserModel {
    @OneToOne(mappedBy = "volunteerId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    DonationRequest request;

    @OneToOne(mappedBy = "volunteerId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    OrganizationDonation donation;
}
