package com.Koupag.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipientModel extends UserModel{
    @OneToOne(mappedBy = "recipientId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    DonationRequest request;

}
