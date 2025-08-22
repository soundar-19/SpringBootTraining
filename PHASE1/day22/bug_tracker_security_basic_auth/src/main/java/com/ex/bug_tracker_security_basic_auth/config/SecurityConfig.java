package com.ex.bug_tracker_security_basic_auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ex.bug_tracker_security_basic_auth.service.JwtAuthFilter;
import com.ex.bug_tracker_security_basic_auth.service.JwtService;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails admin = User.withUsername("admin")
        .password(passwordEncoder.encode("admin123"))
        .roles("ADMIN")
        .build();

        UserDetails developer = User.withUsername("dev")
        .password(passwordEncoder.encode("dev123"))
        .roles("DEVELOPER")
        .build();

        UserDetails user = User.withUsername("SR")
        .password(passwordEncoder.encode("143"))
        .roles("USER")
        .build();

        return new InMemoryUserDetailsManager(admin, developer, user);

    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtService jwtService, InMemoryUserDetailsManager userDetailsService) {
        return new JwtAuthFilter(jwtService, userDetailsService);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/bugs/**").hasAnyRole("ADMIN", "DEVELOPER", "USER")
                .requestMatchers(HttpMethod.PUT, "/api/bugs/*/status").hasAnyRole("ADMIN", "DEVELOPER")
                .requestMatchers(HttpMethod.POST, "/api/bugs/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/bugs/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/bugs/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
}