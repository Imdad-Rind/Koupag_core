package com.Koupag.Repository;

import com.Koupag.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByAuthority(String authority);
}
