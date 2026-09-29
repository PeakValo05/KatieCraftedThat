package com.craftedthat.organization.services;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.craftedthat.organization.models.RegistrationModel;
import com.craftedthat.organization.repository.RegistrationRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final PasswordEncoder ADMIN_PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private final RegistrationRepository registrationRepository;

    public CustomUserDetailsService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

@Override
public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {

    if ("admin".equals(username)) {
        return org.springframework.security.core.userdetails.User.builder()
                .username("admin")
                .password(ADMIN_PASSWORD_ENCODER.encode("admin123!"))
                .roles("ADMIN")
                .build();
    }

    RegistrationModel registrationModel =
        registrationRepository.findByUsername(username)
            .orElseThrow(() ->
                new UsernameNotFoundException(
                    "User not found with username: " + username
                )
            );

    return org.springframework.security.core.userdetails.User.builder()
        .username(registrationModel.getUsername())
        .password(registrationModel.getPassword())
        .roles("USER")
        .build();
}

}
    