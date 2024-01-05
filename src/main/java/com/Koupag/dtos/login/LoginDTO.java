package com.Koupag.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("cnic")
    private String cnic;
    @JsonProperty("password")
    private String password;
}
