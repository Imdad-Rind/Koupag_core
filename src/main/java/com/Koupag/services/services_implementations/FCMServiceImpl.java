package com.Koupag.services.services_implementations;

import com.Koupag.dtos.NotificationDto;
import com.Koupag.services.FCMService;
import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FCMServiceImpl implements FCMService {
    public FCMServiceImpl(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }
    private final FirebaseMessaging firebaseMessaging;


    public String sendANotification(NotificationDto note, String token) throws FirebaseMessagingException {
        Notification notification= Notification
                .builder()
                .setTitle(note.getTitle())
                .setBody(note.getBody())
                .setImage(note.getImageUrl())
                .build();
        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .putAllData(note.getData())
                .build();
        return firebaseMessaging.send(message);
    }
    public BatchResponse sendNotificationToMultipleDevices(NotificationDto note, List<String> tokens) throws FirebaseMessagingException {

        Notification notification= Notification
                .builder()
                .setTitle(note.getTitle())
                .setBody(note.getBody())
                .setImage(note.getImageUrl())
                .build();

        MulticastMessage message = MulticastMessage
                .builder()
                .addAllTokens(tokens)
                .setNotification(notification)
                .putAllData(note.getData())
                .build();
        return firebaseMessaging.sendMulticast(message);
    }
    public void subscribeToTopic(List<String> tokens, String topic) throws FirebaseMessagingException {
        firebaseMessaging.subscribeToTopic(tokens,topic);
    }
    public void unSubscribeToTopic(List<String> tokens, String topic) throws FirebaseMessagingException {
        firebaseMessaging.subscribeToTopic(tokens,topic);
    }
    public String sendNotificationToTopic(NotificationDto note, String topic) throws  FirebaseMessagingException{
        Notification notification= Notification
                .builder()
                .setTitle(note.getTitle())
                .setBody(note.getBody())
                .setImage(note.getImageUrl())
                .build();
        Message message = Message
                .builder()
                .setTopic(topic)
                .setNotification(notification)
                .putAllData(note.getData())
                .build();
        return firebaseMessaging.send(message);
    }
}
