package com.Koupag;

import com.Koupag.dtos.NotificationDto;
import com.Koupag.models.DonationRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Setter
@Getter
@Component
public class AvailableNotifications {
    static private final Map<String, String> data = new HashMap<>();
    static public NotificationDto notifyDonationCreationToVolunteer(DonationRequest donationRequest) {
        data.put("route_to","/home");
        return new NotificationDto(
                donationRequest.getItem().getSurplusMaterial().getName() + " Created",
                donationRequest.getDonor().getName() + " has created the Donation",
                "<imageUrl>",
                data
        );
    }
    static public NotificationDto notifyPickupToDonor(DonationRequest donationRequest) {
        data.put("route_to","/home");
        return new NotificationDto(
                donationRequest.getVolunteer().getName() + " has picked",
                "The volunteer is on the way",
                "<imageUrl>",
                data
        );
    }

    static public NotificationDto notifyUnpickToDonor(DonationRequest donationRequest) {
        data.put("route_to","/home");
        return new NotificationDto(
                donationRequest.getVolunteer().getName() + " has Unpicked your donation",
                "The volunteer disagreed to engage the donation",
                "<imageUrl>",
                data
        );
    }

    static public NotificationDto notifyEngagementToRecipient(DonationRequest donationRequest) {
        data.put("route_to","/home");
        return new NotificationDto(
                donationRequest.getVolunteer().getName() + " has Engaged Donation",
                "The volunteer is on the way",
                "<imageUrl>",
                data
        );
    }

    static public NotificationDto notifySuccessToDonor(DonationRequest donationRequest) {
        data.put("route_to","/home");
        return new NotificationDto(
                donationRequest.getVolunteer().getName() + " has Donated",
                "The volunteer is on the way",
                "<imageUrl>",
                data
        );
    }
}
