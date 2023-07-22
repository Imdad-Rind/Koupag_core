package com.Koupag.Services.ServicesImpl;

import com.Koupag.DTO.SurplusMaterialDTO;
import com.Koupag.Mappers.SurplusMaterialMappper;
import com.Koupag.Model.SurplusMaterialModel;
import com.Koupag.Repository.SurplusMaterialRepository;
import com.Koupag.Services.SurplusMaterialServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurplusMaterialServicesImpl implements SurplusMaterialServices {
    private SurplusMaterialRepository surplusRepo;
    @Autowired
    public SurplusMaterialServicesImpl(SurplusMaterialRepository surplusRepo) {
        this.surplusRepo = surplusRepo;
    }

    @Override
    public void newSurplusMaterialDonationRequest(SurplusMaterialDTO surplus) {
        SurplusMaterialModel surplusMaterial = new SurplusMaterialMappper().DTOtoSurplusMaterial(surplus);
        surplusRepo.save(surplusMaterial);

    }
}
