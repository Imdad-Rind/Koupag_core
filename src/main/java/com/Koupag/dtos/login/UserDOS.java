package com.Koupag.dtos.login;

import com.Koupag.models.Address;
import com.Koupag.models.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class UserDOS implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private long id;
    private String cnic;
    private String phoneNumber;
    private String email;
    private String userType;
    private LocalDateTime lastServed;
    private String name;
    private Address address;

    public UserDOS(User user) {
        user.getAddress().setUser(null);
        this.id = user.getId();
        this.cnic = user.getCNIC();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.userType = user.getAuthorities().stream().toList().get(0).getAuthority();
        this.lastServed = user.getLastServed();
        this.name = user.getName();
        this.address = user.getAddress();
        this.address.setLocation(user.getAddress().getLocation());
    }
}
