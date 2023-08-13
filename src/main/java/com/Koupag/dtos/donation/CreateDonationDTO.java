package com.Koupag.dtos.donation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDonationDTO {
    private long donorId;
    private int count;
    private long surplusMaterialId;
}
