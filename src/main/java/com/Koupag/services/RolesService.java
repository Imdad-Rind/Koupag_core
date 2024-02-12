package com.Koupag.services;

import com.Koupag.models.Roles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RolesService {
    Optional<Roles> getByName(String name);

    List<Roles> getAllRoles();
    List<Roles> getOnlyMainThreeRoles();

    Roles CreateNewRole(Roles roles);
}
