package com.Koupag.Mappers;

import com.Koupag.DTO.RegisterDTO;
import com.Koupag.Model.Roles;
import com.Koupag.Model.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class  UserMapper {

    private PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel DTOtoUser(RegisterDTO user, Set<Roles> authorities){
        UserModel userModel = new UserModel();
        userModel.setName(user.getName());
        userModel.setAuthorities(authorities);
        userModel.setEmailAddress(user.getEmailAddress());
        userModel.setCNIC(user.getCnic());
        userModel.setPhoneNumber(user.getPhoneNumber());
        userModel.setEmailAddress(user.getEmailAddress());
        userModel.setUsername(user.getUsername());
        userModel.setPassword(passwordEncoder.encode(user.getPassword()));
        return userModel;
    }
}
