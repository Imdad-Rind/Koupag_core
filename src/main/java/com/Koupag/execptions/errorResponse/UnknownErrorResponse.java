package com.Koupag.execptions.errorResponse;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class UnknownErrorResponse {
    Integer StatusCode;
    String Message;
    Date TimeStamp;
    Throwable Cause;


}
