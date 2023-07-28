package com.Koupag.Services.ServicesImpl;

import com.Koupag.Model.User;
import com.Koupag.Repository.UserRepository;
import com.Koupag.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicesImpl implements UserService {

    private UserRepository userRepository;
    @Autowired
    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User creteNewUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }



}
