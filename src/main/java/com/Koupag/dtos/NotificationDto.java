package com.Koupag.dtos;

import com.Koupag.models.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Var;

import java.util.Map;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    UUID id;
    String title;
    String body;
    String imageUrl;
    Map<String, String> data;

    public NotificationDto(String title, String body, String imageUrl, Map<String, String> data) {
        this.title = title;
        this.body = body;
        this.imageUrl = imageUrl;
        this.data = data;
    }

    public NotificationDto(Notification notification){
        this.id = notification.getId();
        this.title = notification.getTitle();
        this.body = notification.getBody();
        this.imageUrl = notification.getImageUrl();
    }
}
