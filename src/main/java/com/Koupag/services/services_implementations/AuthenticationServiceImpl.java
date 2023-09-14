package com.Koupag.services.services_implementations;

import com.Koupag.dtos.login.LoginDTO;
import com.Koupag.dtos.login.LoginResponseDTO;
import com.Koupag.models.Address;
import com.Koupag.models.Roles;
import com.Koupag.models.User;
import com.Koupag.repositories.AddressRepository;
import com.Koupag.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserService userService;
    private final RolesService rolesService;
    private final AuthenticationManager authenticationManager;
    private final MyTokenService myTokenService;
    private final UserTypeService userTypeService;
    private final AddressService addressService;

    public AuthenticationServiceImpl(UserService userService, RolesService rolesService, AuthenticationManager authenticationManager, MyTokenService myTokenService, UserTypeService userTypeService, AddressService addressService, AddressRepository addressRepository) {
        this.userService = userService;
        this.rolesService = rolesService;
        this.authenticationManager = authenticationManager;
        this.myTokenService = myTokenService;
        this.userTypeService = userTypeService;
        this.addressService = addressService;
        this.addressRepository = addressRepository;
    }

    private final AddressRepository addressRepository;


    @Override
    public User registerUser(User user) throws Exception {
            Set<Roles> roles = new HashSet<>();
            final var newUserRole = rolesService.getByName(user.getUserType()).get();
            roles.add(newUserRole);
            String userTypeRole = user.getUserType();
            var createdUser = userTypeService.createNewTypeUser(user,roles,userTypeRole);
        System.out.println(createdUser.getAddress().getId());

//            addressService.addAddressByUserId(user.getId(),user.getAddress());
        /*createdUser.setAddress(user.getAddress());*/
//            addressService.addAddressByUserId(createdUser.getId(),user.getAddress());
            if(createdUser == null){
                final User newUseruser = userService.creteNewUser(user);
                if(user == null){
                    throw new Exception("user already exists");
                }
                return user;
            }
        return createdUser;
    }

    @Override
    public Optional<LoginResponseDTO> loginUser(LoginDTO loginDTO) {
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword())
            );

            String token = myTokenService.generateJwt(auth);

           /* //return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
            // return new LoginResponseDTO(userService.getUserByUserName(loginDTO.getUsername()).get(), token);*/
            return Optional.of(new LoginResponseDTO(userService.getUserByUserName(loginDTO.getUsername()).get(), token));

        } catch(AuthenticationException e){
            return Optional.empty();
        }
    }


}
