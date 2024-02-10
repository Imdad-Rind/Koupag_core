package com.Koupag.services.services_implementations;

import com.Koupag.models.*;
import com.Koupag.repositories.RecipientDonationRepository;
import com.Koupag.repositories.RecipientRepository;
import com.Koupag.services.DonorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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
    public List<Recipient> findMostPoor(int count, String city, Location donorLocation) {
        List<Recipient> recipients = recipientRepository.findAllByAddressCityOrderByLastServedAsc(city);
        recipients = findNearestUser(recipients, donorLocation, 0.015000);
        for(Recipient recipient: recipients){
            recipientDonationRepository.save(new RecipientDonation(recipient));
        }
        if(recipients.size() < count) return recipients;
        return recipients.subList(0,count -1);
    }

    //need some work

    public static List<Recipient> findNearestUser(List<Recipient> users, Location origin, double limit) {
        List<Recipient> nearestUsers = new ArrayList<>();

        for (Recipient point : users) {
            double distance = Math.sqrt(Math.pow(point.getAddress().getLocation().getLatitude() - origin.getLatitude(), 2) + Math.pow(point.getAddress().getLocation().getLongitude() - origin.getLongitude(), 2));
            if (distance <= limit) {
                nearestUsers.add(point);
            }
        }
        return nearestUsers;
    }
}
