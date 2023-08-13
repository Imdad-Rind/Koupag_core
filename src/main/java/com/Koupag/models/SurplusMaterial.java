package com.Koupag.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SurplusMaterial{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    String name;
    String description;
    @OneToOne(mappedBy = "surplusMaterial", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    RequestItem requestItem;

}
