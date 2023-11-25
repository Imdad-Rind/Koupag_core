package com.Koupag.mappers.models_map;

import com.Koupag.models.RequestItem;
import com.Koupag.models.SurplusMaterial;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestedMaterialMap {
    Long id;
    int count;
    SurplusMaterialMap surplusMaterial;

    public RequestedMaterialMap(RequestItem item){
        if(item == null) return;
        this.id = item.getId();
        this.count = item.getCount();
        this.surplusMaterial = new SurplusMaterialMap(item.getSurplusMaterial());
    }
}
