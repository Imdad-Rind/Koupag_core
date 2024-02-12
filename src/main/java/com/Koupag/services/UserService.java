package com.Koupag.services;

import com.Koupag.models.Notification;
import com.Koupag.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
public interface UserService {

    User creteNewUser(User user);
    Optional<User> getUserByCNIC(String cnic);
    public Boolean existsByEmail(String email);
    User getUserByEmail(String email);
    void updateUserPassword(UUID id, String newPass);
    Optional<User> getUserById(UUID Id);
    void updateUserById(UUID id, User user);
    List<Notification> getUserNotifications(UUID userId);
    void deleteUserNotification(UUID notificationId);
    void deleteUserByID(UUID id);
    void forgotPasswordUpdate(String cnic, String newPass);
}
