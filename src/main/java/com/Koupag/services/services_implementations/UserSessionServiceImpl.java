package com.Koupag.services.services_implementations;

import com.Koupag.models.UserSessionModel;
import com.Koupag.repositories.UserSessionRepo;
import com.Koupag.services.UserSessionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserSessionServiceImpl implements UserSessionService {

    private final UserSessionRepo userSessionRepo;

    public UserSessionServiceImpl(UserSessionRepo userSessionRepo) {
        this.userSessionRepo = userSessionRepo;
    }

    @Override
    public UserSessionModel registerForNotification(UserSessionModel sessionModel) {
        UserSessionModel userSessionModel = userSessionRepo.findFcmTokensByCard(sessionModel.getCard());
        if(userSessionModel != null){
            userSessionRepo.delete(userSessionModel);
            return userSessionRepo.save(sessionModel);
        }
        return userSessionRepo.save(sessionModel);
    }

    @Override
    public boolean turnOffNotification(long id) {
        Optional<UserSessionModel> userSessionModel = userSessionRepo.findById(id);
        if(userSessionModel.isPresent()){
            userSessionModel.get().setActive(false);
            userSessionRepo.save(userSessionModel.get());
            return false;
        }
        return true;
    }

    @Override
    public boolean turnOnNotification(long id) {
        Optional<UserSessionModel> userSessionModel = userSessionRepo.findById(id);
        if(userSessionModel.isPresent()){
            userSessionModel.get().setActive(true);
            userSessionRepo.save(userSessionModel.get());
            return true;
        }
        return false;
    }

    @Override
    public List<String> getTokensFromCnics(List<String> cnics) {
        List<String> foundTokens = new ArrayList<>();
        for(String cnic: cnics){
            foundTokens.add(userSessionRepo.findFcmTokensByCardAndIsActiveTrue(cnic));
        }
        return foundTokens;
    }

    @Override
    public String getTokenFromCnic(String cnic) {
        return userSessionRepo.findFcmTokensByCardAndIsActiveTrue(cnic);
    }
}
