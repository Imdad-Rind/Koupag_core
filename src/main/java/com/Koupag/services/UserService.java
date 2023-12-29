package com.Koupag.services;

import com.Koupag.models.User;
import com.Koupag.models.UserProfile;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public interface UserService {

    User creteNewUser(User user);
//    UserModel getUserById(long id);
//    Optional<User> getUserByUserName(String username);
    Optional<User> getUserByCNIC(String cnic);
//    Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
    void cacheNewUser(String email, User user);
    User getCachedUser(String email);
    User getUserByEmail(String email);
    
    
}
