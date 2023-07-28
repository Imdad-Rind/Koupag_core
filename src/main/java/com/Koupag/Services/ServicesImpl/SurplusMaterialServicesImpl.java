package com.Koupag.Services.ServicesImpl;
import com.Koupag.Model.SurplusMaterial;
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
    public void newSurplusMaterialDonationRequest(SurplusMaterial surplus) {
        //SurplusMaterial surplusMaterial = new SurplusMaterialMappper().DTOtoSurplusMaterial(surplus);
        surplusRepo.save(surplus);

    }
}
