package com.Koupag.Services.ServicesImpl;

import com.Koupag.DTO.UserDTO;
import com.Koupag.Mappers.UserMapper;
import com.Koupag.Model.UserModel;
import com.Koupag.Repository.UserRepository;
import com.Koupag.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicesImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper mapper;
    @Autowired
    public UserServicesImpl(UserRepository userRepository,UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserModel creteNewUser(UserDTO userDTO) {
        UserModel newUser = mapper.mayToEntity(userDTO);
        userRepository.save(newUser);
        return newUser;
    }

    /*@Override
    public UserDTO getUserById(long id) {
        Optional<UserModel> foundUser = userRepository.findById(id);
        //var userToBeReturned = mayToDTO(foundUser);
        return null ;
    }*/

    @Override
    public Optional<UserModel> getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }



}
