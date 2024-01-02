package com.Koupag.services;

import com.Koupag.dtos.NotificationDto;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FCMService {
    String sendANotification(NotificationDto note, String token) throws FirebaseMessagingException;
    BatchResponse sendNotificationToMultipleDevices(NotificationDto note, List<String> tokens) throws FirebaseMessagingException;
    void subscribeToTopic(List<String> tokens, String topic) throws FirebaseMessagingException;
    void unSubscribeToTopic(List<String> tokens, String topic) throws FirebaseMessagingException;
    String sendNotificationToTopic(NotificationDto note, String topic) throws  FirebaseMessagingException;
}
