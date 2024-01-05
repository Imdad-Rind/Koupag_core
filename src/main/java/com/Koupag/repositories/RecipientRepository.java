package com.Koupag.repositories;

import com.Koupag.models.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, UUID> {
    List<Recipient> findAllByAddressCityOrderByLastServedAsc(String city);
}
