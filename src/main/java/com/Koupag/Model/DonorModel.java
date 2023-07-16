package com.Koupag.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DonorModel extends UserModel{
    @OneToOne(mappedBy = "donorId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    DonationRequest request;

    @OneToOne(mappedBy = "donorId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    OrganizationDonation donation;

}
