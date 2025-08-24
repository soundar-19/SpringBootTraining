package com.sr.shop_pro.config;

import com.sr.shop_pro.security.JwtAuthenticationEntryPoint;
import com.sr.shop_pro.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                
                // Product endpoints - ADMIN can do everything, CUSTOMER/MANAGER can only read
                .requestMatchers("GET", "/api/products/**").hasAnyRole("ADMIN", "CUSTOMER", "MANAGER")
                .requestMatchers("POST", "/api/products").hasRole("ADMIN")
                .requestMatchers("PUT", "/api/products/**").hasRole("ADMIN")
                .requestMatchers("DELETE", "/api/products/**").hasRole("ADMIN")
                
                // Cart endpoints - ADMIN and CUSTOMER access
                .requestMatchers("/api/cart/**").hasAnyRole("ADMIN", "CUSTOMER")
                

                
                // Order endpoints - User access
                .requestMatchers("/api/orders/my-orders/**").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers("/api/orders/complete").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers("GET", "/api/orders/{id}").hasAnyRole("ADMIN", "CUSTOMER")
                
                // Order endpoints - Admin only
                .requestMatchers("GET", "/api/orders").hasRole("ADMIN")
                .requestMatchers("PUT", "/api/orders/*/status").hasRole("ADMIN")
                
                // User management - ADMIN only
                .requestMatchers("/api/users/**").hasRole("ADMIN")
                
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}