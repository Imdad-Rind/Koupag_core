package com.Koupag.services.services_implementations;

import com.Koupag.models.DonationRequest;
import com.Koupag.models.Recipient;
import com.Koupag.models.RecipientDonation;
import com.Koupag.repositories.RecipientDonationRepository;
import com.Koupag.repositories.RecipientRepository;
import com.Koupag.services.DonorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorServiceImpl implements DonorService {

    public DonorServiceImpl(RecipientRepository recipientRepository, RecipientDonationRepository recipientDonationRepository) {
        this.recipientRepository = recipientRepository;
        this.recipientDonationRepository = recipientDonationRepository;
    }

    private final RecipientRepository recipientRepository;
    private final RecipientDonationRepository recipientDonationRepository;

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
        for(Recipient recipient: recipients){
            recipientDonationRepository.save(new RecipientDonation(recipient));
        }
        if(recipients.size() < count) return recipients;
        return recipients.subList(0,count);
    }
}
