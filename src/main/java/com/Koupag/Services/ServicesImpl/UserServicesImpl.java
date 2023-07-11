package com.Koupag.Services.ServicesImpl;

import com.Koupag.DTO.RegisterDTO;
import com.Koupag.Model.Roles;
import com.Koupag.Model.UserModel;
import com.Koupag.Repository.UserRepository;
import com.Koupag.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServicesImpl implements UserService {

    private UserRepository userRepository;
    @Autowired
    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel creteNewUser(UserModel userModel) {
        userRepository.save(userModel);
        return userModel;
    }

    @Override
    public Optional<UserModel> getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }



}
