package com.Koupag.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class UserSessionModel  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty("cnic")
    @Column(unique = true)
    String card;

    @JsonProperty("token")
    @Column(unique = false)
    String fcmToken;

    boolean isActive;

    public UserSessionModel(String card, String fcmToken, boolean isActive) {
        this.card = card;
        this.fcmToken = fcmToken;
        this.isActive = isActive;
    }

}
