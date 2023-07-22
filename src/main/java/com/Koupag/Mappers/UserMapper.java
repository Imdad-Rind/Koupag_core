package com.Koupag.Mappers;

import com.Koupag.DTO.RegisterDTO;
import com.Koupag.Model.*;
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
        userModel.setUserType(user.getUserType());
        userModel.setPhoneNumber(user.getPhoneNumber());
        userModel.setUsername(user.getUsername());
        userModel.setPassword(passwordEncoder.encode(user.getPassword()));
        return userModel;
    }

    public DonorModel DTOToDonor(RegisterDTO user,Set<Roles> authorities){
        DonorModel donor = new DonorModel();
        donor.setName(user.getName());
        donor.setAuthorities(authorities);
        donor.setEmailAddress(user.getEmailAddress());
        donor.setCNIC(user.getCnic());
        donor.setUserType(user.getUserType());
        donor.setPhoneNumber(user.getPhoneNumber());
        donor.setUsername(user.getUsername());
        donor.setPassword(passwordEncoder.encode(user.getPassword()));
        return donor;
    }

    public VolunteerModel DTOToVolunteer(RegisterDTO user,Set<Roles> authorities){
        VolunteerModel volunteer = new VolunteerModel();
        volunteer.setName(user.getName());
        volunteer.setAuthorities(authorities);
        volunteer.setEmailAddress(user.getEmailAddress());
        volunteer.setCNIC(user.getCnic());
        volunteer.setUserType(user.getUserType());
        volunteer.setPhoneNumber(user.getPhoneNumber());
        volunteer.setUsername(user.getUsername());
        volunteer.setPassword(passwordEncoder.encode(user.getPassword()));
        return volunteer;
    }

    public RecipientModel DTOToRecipient(RegisterDTO user,Set<Roles> authorities){
        RecipientModel recipient = new RecipientModel();
        recipient.setName(user.getName());
        recipient.setAuthorities(authorities);
        recipient.setEmailAddress(user.getEmailAddress());
        recipient.setCNIC(user.getCnic());
        recipient.setUserType(user.getUserType());
        recipient.setPhoneNumber(user.getPhoneNumber());
        recipient.setUsername(user.getUsername());
        recipient.setPassword(passwordEncoder.encode(user.getPassword()));
        return recipient;
    }

    public UserModel donorToUserModel(DonorModel donorModel,Set<Roles> authorities){
        UserModel user = new UserModel();
        user.setName(donorModel.getName());
        user.setAuthorities(authorities);
        user.setEmailAddress(donorModel.getEmailAddress());
        user.setCNIC(donorModel.getCNIC());
        user.setUserType(donorModel.getUserType());
        user.setPhoneNumber(donorModel.getPhoneNumber());
        user.setUsername(donorModel.getUsername());
        user.setPassword(passwordEncoder.encode(donorModel.getPassword()));
        return user;
    }
    public UserModel voluntterToUserModel(VolunteerModel Model,Set<Roles> authorities){
        UserModel user = new UserModel();
        user.setName(Model.getName());
        user.setAuthorities(authorities);
        user.setEmailAddress(Model.getEmailAddress());
        user.setCNIC(Model.getCNIC());
        user.setUserType(Model.getUserType());
        user.setPhoneNumber(Model.getPhoneNumber());
        user.setUsername(Model.getUsername());
        user.setPassword(passwordEncoder.encode(Model.getPassword()));
        return user;
    }

    public UserModel recipientToUserModel(RecipientModel Model,Set<Roles> authorities){
        UserModel user = new UserModel();
        user.setName(Model.getName());
        user.setAuthorities(authorities);
        user.setEmailAddress(Model.getEmailAddress());
        user.setCNIC(Model.getCNIC());
        user.setUserType(Model.getUserType());
        user.setPhoneNumber(Model.getPhoneNumber());
        user.setUsername(Model.getUsername());
        user.setPassword(passwordEncoder.encode(Model.getPassword()));
        return user;
    }

}
