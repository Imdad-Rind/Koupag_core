package com.Koupag.services;

import com.Koupag.dtos.NotificationDto;
import com.Koupag.models.Donor;
import com.Koupag.models.Recipient;
import com.Koupag.models.Volunteer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotifyService {
    void donationCreationNotification(List<Volunteer> volunteers, NotificationDto notification);
    void pickupNotification(Donor donor, NotificationDto notification);
    void UnpickNotification(Donor donor, NotificationDto notification);
    void engageNotification(List<Recipient> recipients, NotificationDto notification);
    void completionNotification(Donor donor, NotificationDto notification);
}
