package com.Koupag.Services;

import com.Koupag.DTO.UserDTO;
import com.Koupag.Model.UserModel;

import java.util.Optional;

public interface UserService {

    void creteNewUser(UserDTO userDTO);
    //UserModel getUserById(long id);
    Optional<UserModel> getUserByUserName(String username);
    Boolean existsByUsername(String username);


}
