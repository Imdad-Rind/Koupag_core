package com.Koupag.services.services_implementations;
import com.Koupag.models.SurplusMaterial;
import com.Koupag.repositories.SurplusMaterialRepository;
import com.Koupag.services.SurplusMaterialServices;
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
    public void createNewSurplusMaterial(SurplusMaterial surplus) {
        surplusRepo.save(surplus);
    }
}
