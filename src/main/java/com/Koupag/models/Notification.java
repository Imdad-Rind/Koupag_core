package com.Koupag.models;

import com.Koupag.dtos.NotificationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @Column(name = "to_user")
    UUID userId;
    String title;
    String body;
    @Column(nullable = true)
    String imageUrl;
    LocalDateTime dateTime;

    public Notification (NotificationDto notificationDto, UUID userId){
        this.title = notificationDto.getTitle();
        this.body = notificationDto.getBody();
        this.dateTime = LocalDateTime.now();
        this.imageUrl = notificationDto.getImageUrl();
        this.userId = userId;
    }
}
