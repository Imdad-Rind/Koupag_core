package com.Koupag.Services;

import com.Koupag.Model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public interface UserService {

    User creteNewUser(User user);
    //UserModel getUserById(long id);
    Optional<User> getUserByUserName(String username);
    Boolean existsByUsername(String username);


}
