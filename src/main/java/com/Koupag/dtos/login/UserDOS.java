package com.Koupag.dtos.login;

import com.Koupag.models.Address;
import com.Koupag.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDOS {
    private long id;
    private String cnic;
    private String phoneNumber;
    private String email;
    private String userType;
    private LocalDate lastServed;
    private String name;
    private Address address;

    public UserDOS(User user) {
        user.getAddress().setUser(null);
        this.id = user.getId();
        this.cnic = user.getCNIC();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.userType = user.getUserType();
        this.lastServed = user.getLastServed();
        this.name = user.getName();
        this.address = user.getAddress();
    }
}
