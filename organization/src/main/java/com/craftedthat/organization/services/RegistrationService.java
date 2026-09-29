package com.craftedthat.organization.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.craftedthat.organization.models.RegistrationModel;
import com.craftedthat.organization.repository.RegistrationRepository;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(
            RegistrationRepository registrationRepository,
            PasswordEncoder passwordEncoder) {

        this.registrationRepository = registrationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegistrationModel register(RegistrationModel registrationModel) {

        registrationModel.setPassword(
            passwordEncoder.encode(registrationModel.getPassword())
        );

        return registrationRepository.save(registrationModel);
    }
}