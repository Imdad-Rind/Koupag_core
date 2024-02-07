package com.Koupag.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordDto {
    @JsonProperty("cnic")
    String cnic;
    @JsonProperty("otp")
    String otp;
    @JsonProperty("password")
    String password;
    @JsonProperty("userId")
    UUID userId;
}
