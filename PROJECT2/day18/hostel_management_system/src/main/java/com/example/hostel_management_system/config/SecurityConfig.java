package com.example.hostel_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails admin = User.withUsername("admin")
        .password("{noop}admin")
        .roles("ADMIN")
        .build();

        UserDetails warden = User.withUsername("warden")
        .password("{noop}warden")
        .roles("WARDEN")
        .build();

        UserDetails user = User.withUsername("user")
        .password("{noop}user")
        .roles("USER")
        .build();

        return new InMemoryUserDetailsManager(admin, warden, user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/warden/**").hasRole("WARDEN")
                .requestMatchers("/api/staff/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/students/admin/**").hasRole("ADMIN")
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/api/**").hasAnyRole("USER", "WARDEN", "ADMIN")
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions().sameOrigin())
            .httpBasic();
        
        return http.build();
    }
}