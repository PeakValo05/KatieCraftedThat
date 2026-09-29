package com.craftedthat.organization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.craftedthat.organization.models.RegistrationModel;

public interface RegistrationRepository extends JpaRepository<RegistrationModel, Long> {
    

    Optional<RegistrationModel> findByUsername(String username);
}
