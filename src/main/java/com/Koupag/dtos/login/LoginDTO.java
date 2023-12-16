package com.Koupag.dtos.login;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class LoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String cnic;
    private String password;
}
