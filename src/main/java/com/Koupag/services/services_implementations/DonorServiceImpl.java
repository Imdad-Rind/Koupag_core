package com.Koupag.services.services_implementations;

import com.Koupag.models.DonationRequest;
import com.Koupag.models.Recipient;
import com.Koupag.repositories.RecipientRepository;
import com.Koupag.services.DonorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorServiceImpl implements DonorService {

    public DonorServiceImpl(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    private final RecipientRepository recipientRepository;

    @Override
    public void create_donations(DonationRequest donationRequest) {

    }

    @Override
    public void close_donation() {

    }

    @Override
    public void see_donation_status() {

    }

    @Override
    public List<Recipient> findMostPoor(int count, String city) {
        List<Recipient> recipients = recipientRepository.findAllByAddressCityOrderByLastServedAsc(city);
        if(recipients.size() < count) return recipients;
        return recipients.subList(0,count);
    }
}
