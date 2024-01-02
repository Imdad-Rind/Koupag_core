package com.Koupag.services;

import com.Koupag.models.UserSessionModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserSessionService {

    UserSessionModel registerForNotification(UserSessionModel sessionModel);
    boolean turnOffNotification(long id);
    boolean turnOnNotification(long id);
    List<String> getTokensFromCnics(List<String> cnics);

    String getTokenFromCnic(String cnic);
}
