package com.Koupag.services.services_implementations;

import com.Koupag.dtos.NotificationDto;
import com.Koupag.models.*;
import com.Koupag.repositories.NotificationRepository;
import com.Koupag.repositories.UserSessionRepo;
import com.Koupag.services.FCMService;
import com.Koupag.services.NotifyService;
import com.Koupag.services.UserSessionService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NotifyServiceImpl implements NotifyService {

    private final UserSessionService userSessionService;
    private final NotificationRepository notificationRepository;
    private final FCMService fcmService;

    public NotifyServiceImpl(UserSessionService userSessionService, FCMService fcmService, UserSessionRepo userSessionRepo, NotificationRepository notificationRepository) {
        this.userSessionService = userSessionService;
        this.fcmService = fcmService;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void donationCreationNotification(List<Volunteer> volunteers, NotificationDto notification) {
        if(!volunteers.isEmpty()){
            List<String> cnics = volunteers.stream().map(User::getCNIC).toList();
            List<String> tokens = userSessionService.getTokensFromCnics(cnics);
            try {
                fcmService.sendNotificationToMultipleDevices(
                        notification,
                        tokens
                );
                volunteers.forEach(e -> notificationRepository.save(new Notification(notification, e.getId())));
            } catch (Exception e){
                return;
            }
        }
    }

    @Override
    public void pickupNotification(Donor donor, NotificationDto notification) {
        // sending notification to donor for pickup
        try {
            String fcmToken = userSessionService.getTokenFromCnic(donor.getCNIC());
            fcmService.sendANotification(
                    notification,
                    fcmToken
            );
            notificationRepository.save(new Notification(notification, donor.getId()));
        } catch (FirebaseMessagingException e) {
            return;
        } catch (Exception e){
            return;
        }
    }

    @Override
    public void UnpickNotification(Donor donor, NotificationDto notification) {
        try{
            String fcmToken = userSessionService.getTokenFromCnic(donor.getCNIC());
            // sending notification to donor for pickup
            fcmService.sendANotification(
                    notification,
                    fcmToken
            );
            notificationRepository.save(new Notification(notification, donor.getId()));
        } catch (FirebaseMessagingException e){
            return;
        } catch (Exception e){
            return;
        }
    }

    @Override
    public void engageNotification(List<Recipient> recipients, NotificationDto notification) {
        // sending notification to donor for pickup
        try {
            List<String> fcmTokens = userSessionService.getTokensFromCnics(recipients.stream().map(User::getCNIC).toList());
            fcmService.sendNotificationToMultipleDevices(
                    notification,
                    fcmTokens
            );
            recipients.forEach(e -> notificationRepository.save(new Notification(notification, e.getId())));
        } catch (FirebaseMessagingException e){
            return;
        } catch (Exception e){
            return;
        }
    }

    @Override
    public void completionNotification(Donor donor, NotificationDto notification) {
        String fcmToken = userSessionService.getTokenFromCnic(donor.getCNIC());
        // sending notification to donor for pickup
        try{
            fcmService.sendANotification(
                    notification,
                    fcmToken
            );
            notificationRepository.save(new Notification(notification, donor.getId()));
        } catch (FirebaseMessagingException e){
            throw new NoSuchElementException("No Recipient Found");
        } catch (Exception e){
            return;
        }
    }
}
