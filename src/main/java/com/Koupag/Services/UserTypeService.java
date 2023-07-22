package com.Koupag.Services;

import com.Koupag.DTO.RegisterDTO;
import com.Koupag.Model.*;

import java.util.Set;

public interface UserTypeService {

    UserModel creteNewTypeUser(RegisterDTO registerDTO, Set<Roles> roles, String userTypeRole);
    void creteDonorTypeUser(DonorModel donorModel);
    void creteVolunteerTypeUser(VolunteerModel volunteerModel);
    void creteRecipientTypeUser(RecipientModel recipientModel);
}
