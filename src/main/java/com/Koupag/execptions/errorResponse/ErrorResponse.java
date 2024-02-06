package com.Koupag.execptions.errorResponse;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    Integer StatusCode;
    String Message;
    Date TimeStamp;
}
