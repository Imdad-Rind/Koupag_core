package com.Koupag.Model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Roles {
    @Id
   /* @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private int id;
    private String name;
}
