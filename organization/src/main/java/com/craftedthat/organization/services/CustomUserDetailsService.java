package com.craftedthat.organization.services;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.craftedthat.organization.models.RegistrationModel;
import com.craftedthat.organization.repository.RegistrationRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RegistrationRepository registrationRepository;

    public CustomUserDetailsService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

@Override
public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {

    System.out.println("LOGIN ATTEMPT: " + username);

    RegistrationModel registrationModel =
        registrationRepository.findByUsername(username)
            .orElseThrow(() ->
                new UsernameNotFoundException(
                    "User not found with username: " + username
                )
            );

    System.out.println("USER FOUND: " + registrationModel.getUsername());
    System.out.println("PASSWORD FROM DB: " + registrationModel.getPassword());

    return org.springframework.security.core.userdetails.User.builder()
        .username(registrationModel.getUsername())
        .password(registrationModel.getPassword())
        .roles("USER")
        .build();
}

}
    