package com.Koupag.mappers.models_map;

import com.Koupag.models.Donor;
import com.Koupag.models.Recipient;
import com.Koupag.models.User;
import com.Koupag.models.Volunteer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserMap {
    UUID id;
    private String CNIC;
    private String phoneNumber;
    private String email;
    private String userType;
    private LocalDateTime lastServed;
    private String name;
    private AddressMap address;

    public UserMap(Donor user){
        if(user == null) return;
        this.id = user.getId();
        this.name = user.getName();
        this.CNIC = user.getCNIC();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.userType = user.getAuthorities().stream().toList().get(0).getAuthority();
        this.lastServed = user.getLastServed();
        this.address = new AddressMap(user.getAddress());
    }
    public UserMap(Volunteer user){
        if(user == null) return;
        this.id = user.getId();
        this.name = user.getName();
        this.CNIC = user.getCNIC();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.userType = user.getAuthorities().stream().toList().get(0).getAuthority();
        this.lastServed = user.getLastServed();
        this.address = new AddressMap(user.getAddress());
    }
    public UserMap(Recipient user){
        if(user == null) return;
        this.id = user.getId();
        this.name = user.getName();
        this.CNIC = user.getCNIC();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.userType = user.getAuthorities().stream().toList().get(0).getAuthority();
        this.lastServed = user.getLastServed();
        this.address = new AddressMap(user.getAddress());
    }
    public UserMap(User user){
        if(user == null) return;
        this.id = user.getId();
        this.name = user.getName();
        this.CNIC = user.getCNIC();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.userType = user.getAuthorities().stream().toList().get(0).getAuthority();
        this.lastServed = user.getLastServed();
        this.address = new AddressMap(user.getAddress());
    }


}
