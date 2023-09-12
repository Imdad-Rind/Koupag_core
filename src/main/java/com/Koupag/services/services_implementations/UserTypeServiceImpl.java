package com.Koupag.services.services_implementations;

import com.Koupag.mappers.UserMapper;
import com.Koupag.models.*;
import com.Koupag.repositories.DonorRepository;
import com.Koupag.repositories.RecipientRepository;
import com.Koupag.repositories.VolunteerRepository;
import com.Koupag.services.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserTypeServiceImpl implements UserTypeService {


    private final DonorRepository donorRepository;
    private final RecipientRepository recipientRepository;
    private final VolunteerRepository volunteerRepository;
    private final UserMapper mapper;

    @Autowired
    public UserTypeServiceImpl(DonorRepository donorRepository,
                               RecipientRepository recipientRepository, VolunteerRepository volunteerRepository, UserMapper mapper) {
        this.donorRepository = donorRepository;
        this.recipientRepository = recipientRepository;
        this.volunteerRepository = volunteerRepository;
        this.mapper = mapper;
    }


    @Override
    public User createNewTypeUser(User user, Set<Roles> roles, String userTypeRole) {
        switch (userTypeRole) {
            case "DONOR" -> {
                var donorUser = mapper.userToDonor(user, roles);
                creteDonorTypeUser(donorUser);
                return mapper.donorToUserModel(donorUser, roles);
            }
            case "VOLUNTEER" -> {
                var volunteerUser = mapper.userToVolunteer(user, roles);
                creteVolunteerTypeUser(volunteerUser);
                return mapper.volunteerToUserModel(volunteerUser, roles);
            }
            case "RECIPIENT" -> {
                var recipientUser = mapper.userToRecipient(user, roles);
                creteRecipientTypeUser(recipientUser);
                return mapper.recipientToUserModel(recipientUser, roles);
            }
        }
        return null;
    }

    @Override
    public void creteDonorTypeUser(Donor donor) {
        donorRepository.save(donor);
    }

    @Override
    public void creteVolunteerTypeUser(Volunteer volunteerModel) {
        volunteerRepository.save(volunteerModel);
    }

    @Override
    public void creteRecipientTypeUser(Recipient recipient) {
        recipientRepository.save(recipient);
    }
}
