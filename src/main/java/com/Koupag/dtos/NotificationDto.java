package com.Koupag.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Var;

import java.util.Map;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    String title;
    String body;
    String imageUrl;
    Map<String, String> data;
}
