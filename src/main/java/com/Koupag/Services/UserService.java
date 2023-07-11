package com.Koupag.Services;

import com.Koupag.Model.UserModel;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public interface UserService {

    UserModel creteNewUser(UserModel userModel);
    //UserModel getUserById(long id);
    Optional<UserModel> getUserByUserName(String username);
    Boolean existsByUsername(String username);


}
