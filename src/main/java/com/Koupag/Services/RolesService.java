package com.Koupag.Services;

import com.Koupag.Model.Roles;

import java.util.Optional;

public interface RolesService {
    Optional<Roles> getByName(String name);

    Roles CreateNewRole(Roles roles);
}
