package com.Koupag.services;

import com.Koupag.models.Roles;

import java.util.List;
import java.util.Optional;

public interface RolesService {
    Optional<Roles> getByName(String name);
    Optional<Roles> getRolesById(int id);

    List<Roles> getAllRoles();

    Roles CreateNewRole(Roles roles);
}
