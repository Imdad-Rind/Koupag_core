package com.Koupag.mappers;

import com.Koupag.mappers.models_map.UserMap;
import com.Koupag.models.RecipientDonation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecipientDonationMap {
    UserMap recipient;
    LocalDateTime donationDateTime;
    RecipientDonationMap(RecipientDonation recipientDonation){
        this.recipient = new UserMap(recipientDonation.getRecipient());
        donationDateTime = recipientDonation.getDonationDateTime();
    }
}
