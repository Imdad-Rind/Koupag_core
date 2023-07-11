package com.Koupag.DTO;

import com.Koupag.Model.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String username;
    private String password;


}
