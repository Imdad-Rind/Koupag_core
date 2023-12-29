package com.Koupag.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SurplusMaterial{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String description;
    @ManyToMany(mappedBy = "surplusMaterial", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    List<RequestItem> requestItems;

    public SurplusMaterial(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
