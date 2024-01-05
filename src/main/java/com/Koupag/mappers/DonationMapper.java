package com.Koupag.mappers;

import com.Koupag.mappers.models_map.LocationMap;
import com.Koupag.mappers.models_map.RequestedMaterialMap;
import com.Koupag.mappers.models_map.UserMap;
import com.Koupag.models.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DonationMapper {
    UUID id;
    UserMap donor;
    List<RecipientDonationMap> RecipientDonations;
    UserMap volunteer;
    RequestedMaterialMap requestItem;

    String description;
    Boolean isDonationActive;
    LocationMap location;

    LocalDateTime creationDateAndTime;                  // The time a Donation Request is created
    String expectedPickupTime;                   // The time a Donor fix to be picked
    LocalDateTime volunteerPickupTime;                  // The time a Volunteer announces to pick that donation
    LocalDateTime engagedDateAndTime;                   // The time a Volunteer Actually pickup the donation
            // The time a Volunteer hand over the donation
    public DonationMapper(DonationRequest request){
        if(request == null) return;
        this.id = request.getId();
        this.donor = new UserMap(request.getDonor());
        this.volunteer = new UserMap(request.getVolunteer());
        this.RecipientDonations = request.getRecipientDonations().stream().map(RecipientDonationMap::new).toList();
        this.requestItem = new RequestedMaterialMap(request.getRequestItem());
        this.description = request.getDescription();
        this.location = new LocationMap(request.getLocation());
        this.expectedPickupTime = request.getExpectedPickupTime();
        this.volunteerPickupTime = request.getVolunteerPickupTime();
        this.isDonationActive = request.getIsDonationActive();
        this.creationDateAndTime = request.getCreationDateAndTime();
        this.engagedDateAndTime = request.getEngagedDateTime();
    }

}
