package com.Koupag.services;

import com.Koupag.models.User;
import com.Koupag.models.UserProfile;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public interface UserService {

    User creteNewUser(User user);
    //UserModel getUserById(long id);
    Optional<User> getUserByUserName(String username);
    Boolean existsByUsername(String username);
    void cacheNewUser(String email, User user);
    User getCachedUser(String email);
    User getUserByEmail(String email);
    void createUserProfileByUserId(UserProfile userProfile, Long id);
    
    
}
