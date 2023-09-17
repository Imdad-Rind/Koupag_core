package com.Koupag.mappers;

import com.Koupag.models.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class  UserMapper {

    private final PasswordEncoder passwordEncoder;


    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
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
        donor.setAddress(new Address(
                user.getAddress().getAreaName(),
                user.getAddress().getUcName(),
                user.getAddress().getCityName(),
                donor
        ));
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
        volunteer.setAddress(
                new Address(
                        user.getAddress().getAreaName(),
                        user.getAddress().getUcName(),
                        user.getAddress().getCityName(),
                        volunteer
                )
        );
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
        recipient.setAddress(
                new Address(
                        user.getAddress().getAreaName(),
                        user.getAddress().getUcName(),
                        user.getAddress().getCityName(),
                        recipient
                )
        );
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
        user.setAddress(donor.getAddress());
        return user;
    }
    public User volunteerToUserModel(Volunteer volunteer, Set<Roles> authorities){
        User user = new User();
        user.setName(volunteer.getName());
        user.setAuthorities(authorities);
        user.setEmailAddress(volunteer.getEmailAddress());
        user.setCNIC(volunteer.getCNIC());
        user.setUserType(volunteer.getUserType());
        user.setPhoneNumber(volunteer.getPhoneNumber());
        user.setUsername(volunteer.getUsername());
        user.setPassword(passwordEncoder.encode(volunteer.getPassword()));
        user.setAddress(
                new Address(
                        volunteer.getAddress().getAreaName(),
                        volunteer.getAddress().getUcName(),
                        volunteer.getAddress().getCityName(),
                        user
                )
        );
        return user;
    }

    public User recipientToUserModel(Recipient recipient, Set<Roles> authorities){
        User user = new User();
        user.setName(recipient.getName());
        user.setAuthorities(authorities);
        user.setEmailAddress(recipient.getEmailAddress());
        user.setCNIC(recipient.getCNIC());
        user.setUserType(recipient.getUserType());
        user.setPhoneNumber(recipient.getPhoneNumber());
        user.setUsername(recipient.getUsername());
        user.setPassword(passwordEncoder.encode(recipient.getPassword()));
        user.setAddress(
                new Address(
                        recipient.getAddress().getAreaName(),
                        recipient.getAddress().getUcName(),
                        recipient.getAddress().getCityName(),
                        user
                )
        );
        return user;
    }

}
