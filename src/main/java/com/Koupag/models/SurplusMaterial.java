package com.Koupag.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SurplusMaterial{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    String description;
    @ManyToMany(mappedBy = "surplusMaterial", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    List<RequestItem> requestItemList;

    public SurplusMaterial(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
