package com.Koupag.Services.ServicesImpl;

import com.Koupag.Model.Roles;
import com.Koupag.Repository.RolesRepository;
import com.Koupag.Services.RolesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RolesServiceImpl implements RolesService {
    private final RolesRepository rolesRepository;

    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public Optional<Roles> getByName(String name) {
        return rolesRepository.findByAuthority(name);
    }

    @Override
    public Optional<Roles> getRolesById(int id) {
        return rolesRepository.findById(id);
    }

    @Override
    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public Roles CreateNewRole(Roles roles) {
        rolesRepository.save(roles);
        return roles;
    }
}
