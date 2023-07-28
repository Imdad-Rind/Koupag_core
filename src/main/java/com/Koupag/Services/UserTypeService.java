package com.Koupag.Services;

import com.Koupag.Model.*;

import java.util.Set;

public interface UserTypeService {

    User creteNewTypeUser(User user, Set<Roles> roles, String userTypeRole);
    void creteDonorTypeUser(Donor donor);
    void creteVolunteerTypeUser(Volunteer volunteerModel);
    void creteRecipientTypeUser(Recipient recipient);
}
