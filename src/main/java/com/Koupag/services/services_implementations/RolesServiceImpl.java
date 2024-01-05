package com.Koupag.services.services_implementations;

import com.Koupag.models.Roles;
import com.Koupag.repositories.RolesRepository;
import com.Koupag.services.RolesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Optional<Roles> getRolesById(UUID id) {
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
