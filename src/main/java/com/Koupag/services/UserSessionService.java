package com.Koupag.services;

import com.Koupag.models.UserSessionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserSessionService {

    UserSessionModel registerForNotification(UserSessionModel sessionModel);
    boolean turnOffNotification(UUID id);
    boolean turnOnNotification(UUID id);
    List<String> getTokensFromCnics(List<String> cnics);

    String getTokenFromCnic(String cnic);
}
