package com.Koupag.mappers.models_map;

import com.Koupag.models.SurplusMaterial;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SurplusMaterialMap {
    UUID id;
    String name;
    String description;

    public SurplusMaterialMap(SurplusMaterial material){
        if(material == null) return;
        this.id = material.getId();
        this.name = material.getName();
        this.description = material.getDescription();
    }
}
