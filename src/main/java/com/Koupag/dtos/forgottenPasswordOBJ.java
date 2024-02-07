package com.Koupag.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class forgottenPasswordOBJ {
    @JsonProperty("cnic")
    String cnic;
    @JsonProperty("otp")
    String otp;
    @JsonProperty("pass")
    String pass;
}
