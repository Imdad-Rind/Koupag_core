package com.Koupag.mappers;

import com.Koupag.dtos.address.addressDTO;
import com.Koupag.dtos.register.RegisterRequest;
import com.Koupag.models.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class  UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final addressMapper addressMapper;


    public UserMapper(PasswordEncoder passwordEncoder, com.Koupag.mappers.addressMapper addressMapper) {
        this.passwordEncoder = passwordEncoder;
        this.addressMapper = addressMapper;
    }
    
    public User fromRecordToUser(RegisterRequest request ){
        User user = new User();
        user.setName(request.name());
        user.setEmailAddress(request.emailAddress());
        user.setCNIC(request.cnic());
        user.setUserType(request.userType());
        user.setPhoneNumber(request.phoneNumber());
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setAddress(new Address(
                request.address().getAreaName(),
                request.address().getUcName(),
                request.address().getCityName()
        ));
        return user;
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
        donor.setAddress(addressMapper.dtoToAddress(new addressDTO(
                user.getAddress().getAreaName(),
                user.getAddress().getUcName(),
                user.getAddress().getCityName()
        
        )));
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
        volunteer.setAddress( addressMapper.dtoToAddress(new addressDTO(
                user.getAddress().getAreaName(),
                user.getAddress().getUcName(),
                user.getAddress().getCityName()
        
        )));
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
        recipient.setAddress(addressMapper.dtoToAddress(new addressDTO(
                user.getAddress().getAreaName(),
                user.getAddress().getUcName(),
                user.getAddress().getCityName()
        
        )));
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
        user.setAddress(addressMapper.dtoToAddress(new addressDTO(
                donor.getAddress().getAreaName(),
                donor.getAddress().getUcName(),
                donor.getAddress().getCityName()
        
        )));
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
        user.setAddress(addressMapper.dtoToAddress(new addressDTO(
                volunteer.getAddress().getAreaName(),
                volunteer.getAddress().getUcName(),
                volunteer.getAddress().getCityName()
        
        )));
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
        user.setAddress(addressMapper.dtoToAddress(new addressDTO(
                recipient.getAddress().getAreaName(),
                recipient.getAddress().getUcName(),
                recipient.getAddress().getCityName()
        
        )));
        return user;
    }

}
