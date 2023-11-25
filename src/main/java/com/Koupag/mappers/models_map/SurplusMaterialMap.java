package com.Koupag.mappers.models_map;

import com.Koupag.models.SurplusMaterial;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurplusMaterialMap {
    String name;
    String description;

    public SurplusMaterialMap(SurplusMaterial material){
        if(material == null) return;
        this.name = material.getName();
        this.description = material.getDescription();
    }
}
