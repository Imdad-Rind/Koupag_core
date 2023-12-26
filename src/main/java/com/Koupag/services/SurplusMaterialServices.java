package com.Koupag.services;

import com.Koupag.models.SurplusMaterial;

import java.util.List;

public interface SurplusMaterialServices {
    void createNewSurplusMaterial(SurplusMaterial surplus);

    List<String> getAllSurplusMaterialsName();
}
