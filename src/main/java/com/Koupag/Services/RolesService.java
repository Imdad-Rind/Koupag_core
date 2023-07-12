package com.Koupag.Services;

import com.Koupag.Model.Roles;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RolesService {
    Optional<Roles> getByName(String name);
    Optional<Roles> getRolesById(int id);

    List<Roles> getAllRoles();

    Roles CreateNewRole(Roles roles);
}
