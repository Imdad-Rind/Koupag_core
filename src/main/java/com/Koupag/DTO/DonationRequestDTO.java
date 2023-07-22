package com.Koupag.DTO;

import com.Koupag.Model.DonorModel;
import com.Koupag.Model.RecipientModel;
import com.Koupag.Model.RequestItemModel;
import com.Koupag.Model.VolunteerModel;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequestDTO {
    DonorModel donorId;
    RecipientModel recipientId;
    VolunteerModel volunteerId;
    RequestItemModel requestItemId;
    int status;
    LocalDateTime creationDateAndTime;
    LocalDateTime engagedDateAndTime;
    LocalDateTime successfulDonationDateAndTime;
}
