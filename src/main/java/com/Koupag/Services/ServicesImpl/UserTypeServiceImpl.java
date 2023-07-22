package com.Koupag.Services.ServicesImpl;

import com.Koupag.DTO.RegisterDTO;
import com.Koupag.Mappers.UserMapper;
import com.Koupag.Model.*;
import com.Koupag.Repository.DonorRepository;
import com.Koupag.Repository.RecipientRepository;
import com.Koupag.Repository.VolunteerRepository;
import com.Koupag.Services.UserTypeService;
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
    public UserModel creteNewTypeUser(RegisterDTO registerDTO, Set<Roles> roles, String userTypeRole) {
        switch (userTypeRole) {
            case "DONOR" -> {
                var donorUser = mapper.DTOToDonor(registerDTO, roles);
                creteDonorTypeUser(donorUser);
                return mapper.donorToUserModel(donorUser, roles);
            }
            case "VOLUNTEER" -> {
                var volunteerUser = mapper.DTOToVolunteer(registerDTO, roles);
                creteVolunteerTypeUser(volunteerUser);
                return mapper.voluntterToUserModel(volunteerUser, roles);
            }
            case "RECIPIENT" -> {
                var recipientUser = mapper.DTOToRecipient(registerDTO, roles);
                creteRecipientTypeUser(recipientUser);
                return mapper.recipientToUserModel(recipientUser, roles);
            }
        }
        return null;
    }

    @Override
    public void creteDonorTypeUser(DonorModel donorModel) {
        donorRepository.save(donorModel);
    }

    @Override
    public void creteVolunteerTypeUser(VolunteerModel volunteerModel) {
        volunteerRepository.save(volunteerModel);
    }

    @Override
    public void creteRecipientTypeUser(RecipientModel recipientModel) {
        recipientRepository.save(recipientModel);
    }
}
