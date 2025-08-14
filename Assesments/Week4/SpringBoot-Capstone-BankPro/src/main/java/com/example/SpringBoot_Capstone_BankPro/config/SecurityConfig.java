package com.example.SpringBoot_Capstone_BankPro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin123"))
            .roles("ADMIN")
            .build();
            
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("user123"))
            .roles("USER")
            .build();
            
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("GET", "/api/customers/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("POST", "/api/customers/**").hasRole("ADMIN")
                .requestMatchers("PUT", "/api/customers/**").hasRole("ADMIN")
                .requestMatchers("DELETE", "/api/customers/**").hasRole("ADMIN")
                .requestMatchers("GET", "/api/accounts/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("POST", "/api/accounts/**").hasRole("ADMIN")
                .requestMatchers("PUT", "/api/accounts/**").hasRole("ADMIN")
                .requestMatchers("DELETE", "/api/accounts/**").hasRole("ADMIN")
                .requestMatchers("GET", "/api/transactions/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("POST", "/api/transactions/**").hasRole("ADMIN")
                .requestMatchers("DELETE", "/api/transactions/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(basic -> {})
            .headers(headers -> headers.frameOptions().disable());
        
        return http.build();
    }
}