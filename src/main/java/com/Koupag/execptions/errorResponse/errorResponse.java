package com.Koupag.execptions.errorResponse;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class errorResponse {
    Integer StatusCode;
    String Message;
    Date TimeStamp;
}
