package com.Koupag.DTO;

import com.Koupag.Model.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String name;
    private String cnic;
    private String phoneNumber;
    private String emailAddress;
    private String username;
    private String password;
    private String userType;

}
