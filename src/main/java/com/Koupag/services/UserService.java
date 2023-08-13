package com.Koupag.services;

import com.Koupag.models.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public interface UserService {

    User creteNewUser(User user);
    //UserModel getUserById(long id);
    Optional<User> getUserByUserName(String username);
    Boolean existsByUsername(String username);


}
