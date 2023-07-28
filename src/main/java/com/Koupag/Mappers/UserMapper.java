package com.Koupag.Mappers;

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

  /*  public User DTOtoUser(RegisterDTO user, Set<Roles> authorities){
        User userModel = new User();
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
*/
    public Donor userToDonor(User user, Set<Roles> authorities){
        Donor donor = new Donor();
        donor.setName(user.getName());
        donor.setAuthorities(authorities);
        donor.setEmailAddress(user.getEmailAddress());
        donor.setCNIC(user.getCNIC());
        donor.setUserType(user.getUserType());
        donor.setPhoneNumber(user.getPhoneNumber());
        donor.setUsername(user.getUsername());
        donor.setPassword(passwordEncoder.encode(user.getPassword()));
        return donor;
    }

    public Volunteer userToVolunteer(User user, Set<Roles> authorities){
        Volunteer volunteer = new Volunteer();
        volunteer.setName(user.getName());
        volunteer.setAuthorities(authorities);
        volunteer.setEmailAddress(user.getEmailAddress());
        volunteer.setCNIC(user.getCNIC());
        volunteer.setUserType(user.getUserType());
        volunteer.setPhoneNumber(user.getPhoneNumber());
        volunteer.setUsername(user.getUsername());
        volunteer.setPassword(passwordEncoder.encode(user.getPassword()));
        return volunteer;
    }

    public Recipient userToRecipient(User user, Set<Roles> authorities){
        Recipient recipient = new Recipient();
        recipient.setName(user.getName());
        recipient.setAuthorities(authorities);
        recipient.setEmailAddress(user.getEmailAddress());
        recipient.setCNIC(user.getCNIC());
        recipient.setUserType(user.getUserType());
        recipient.setPhoneNumber(user.getPhoneNumber());
        recipient.setUsername(user.getUsername());
        recipient.setPassword(passwordEncoder.encode(user.getPassword()));
        return recipient;
    }

    public User donorToUserModel(Donor donor, Set<Roles> authorities){
        User user = new User();
        user.setName(donor.getName());
        user.setAuthorities(authorities);
        user.setEmailAddress(donor.getEmailAddress());
        user.setCNIC(donor.getCNIC());
        user.setUserType(donor.getUserType());
        user.setPhoneNumber(donor.getPhoneNumber());
        user.setUsername(donor.getUsername());
        user.setPassword(passwordEncoder.encode(donor.getPassword()));
        return user;
    }
    public User voluntterToUserModel(Volunteer Model, Set<Roles> authorities){
        User user = new User();
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

    public User recipientToUserModel(Recipient Model, Set<Roles> authorities){
        User user = new User();
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
