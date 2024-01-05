package com.Koupag.mappers.models_map;

import com.Koupag.models.RequestItem;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RequestedMaterialMap {
    UUID id;
    int count;
    SurplusMaterialMap surplusMaterial;

    public RequestedMaterialMap(RequestItem item){
        if(item == null) return;
        this.id = item.getId();
        this.count = item.getCount();
        this.surplusMaterial = new SurplusMaterialMap(item.getSurplusMaterial());
    }
}
