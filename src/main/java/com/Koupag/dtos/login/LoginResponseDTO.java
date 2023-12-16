package com.Koupag.dtos.login;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class LoginResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UserDOS user;
    private String jwt;
}
