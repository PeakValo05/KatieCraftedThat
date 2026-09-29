package com.craftedthat.organization.config;

import org.springframework.boot.security.autoconfigure.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;    

import com.craftedthat.organization.services.CustomUserDetailsService;    


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


    http
        .userDetailsService(customUserDetailsService)


        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login", "/register", "/register/**").permitAll()
            .requestMatchers("/css/**", "/images/**", "/js/**").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )


.formLogin(form -> form
    .loginPage("/login")

    .successHandler((request, response, authentication) -> {

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            response.sendRedirect("/admin/subscribers");
        } else {
            response.sendRedirect("/home");
        }
    })

    .failureUrl("/login?error")
    .permitAll()


        )
        .logout(logout -> logout.permitAll());

    return http.build();
}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


@Bean
public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

    UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin123!"))
            .roles("ADMIN")
            .build();

    return new InMemoryUserDetailsManager(admin);
}
}
