package com.Koupag.repositories;

import com.Koupag.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
//    Optional<User> findByUsername(String username) throws Exception;
    Boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByCNIC(String cnic);


}
