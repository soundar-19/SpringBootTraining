package com.ex.bug_tracker_security_basic_auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails admin = User.withUsername("admin")
        .password("{noop}admin123")
        .roles("ADMIN")
        .build();

        UserDetails user = User.withUsername("SR")
        .password("{noop}143")
        .roles("USER")
        .build();

        return new InMemoryUserDetailsManager(admin,user);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/bugs/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/bugs/user/**").hasRole("USER") 
                .requestMatchers("/api/bugs/**").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {});
        
        return http.build();
    }
}