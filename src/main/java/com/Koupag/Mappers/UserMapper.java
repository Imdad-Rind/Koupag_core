package com.Koupag.Mappers;

import com.Koupag.DTO.UserDTO;
import com.Koupag.Model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO mayToDTO(UserModel user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public UserModel mayToEntity(UserDTO userDTO){
        UserModel userModel = new UserModel();
        userModel.setId(userDTO.getId());
        userModel.setUsername(userDTO.getUsername());
        userModel.setPassword(userDTO.getPassword());
        return userModel;
    }
}
