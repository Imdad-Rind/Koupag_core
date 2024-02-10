package com.Koupag.services;

import com.Koupag.models.DonationRequest;
import com.Koupag.models.Location;
import com.Koupag.models.Recipient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public interface NearbyService {
    public static List<Recipient> findNearestUser(List<Recipient> users, Location origin, double limit){
        List<Recipient> nearestUsers = new ArrayList<>();

        for (Recipient point : users) {
            double distance = Math.sqrt(Math.pow(point.getAddress().getLocation().getLatitude() - origin.getLatitude(), 2) + Math.pow(point.getAddress().getLocation().getLongitude() - origin.getLongitude(), 2));
            if (distance <= limit) {
                nearestUsers.add(point);
            }
        }
        return nearestUsers;
    }

    public static List<DonationRequest> findNearestDonation(List<DonationRequest> donations, Location origin, double limit){
        List<DonationRequest> nearestDonations = new ArrayList<>();

        for (DonationRequest request : donations) {
            double distance = Math.sqrt(Math.pow(request.getLocation().getLatitude() - origin.getLatitude(), 2) + Math.pow(request.getLocation().getLongitude() - origin.getLongitude(), 2));
            if (distance <= limit) {
                nearestDonations.add(request);
            }
        }
        return nearestDonations;
    }

}
