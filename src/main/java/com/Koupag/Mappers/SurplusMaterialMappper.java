package com.Koupag.Mappers;

import com.Koupag.DTO.SurplusMaterialDTO;
import com.Koupag.Model.SurplusMaterialModel;
import org.springframework.stereotype.Component;

@Component
public class SurplusMaterialMappper {

    public SurplusMaterialModel DTOtoSurplusMaterial(SurplusMaterialDTO surplusMaterialDTO){
        SurplusMaterialModel surplus = new SurplusMaterialModel();
        surplus.setMaterialType(surplusMaterialDTO.getMaterialType());
        surplus.setCount(surplusMaterialDTO.getCount());
        surplus.setDescription(surplusMaterialDTO.getDescription());
        return surplus;
    }
}
