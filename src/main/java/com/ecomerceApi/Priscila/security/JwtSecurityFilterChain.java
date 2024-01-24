package com.ecomerceApi.Priscila.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ecomerceApi.Priscila.model.Permission.*;
import static com.ecomerceApi.Priscila.model.Role.CUSTOMER;

@Configuration
@AllArgsConstructor
public class JwtSecurityFilterChain {

    public JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // protect against fake solicitations
                .securityMatchers((matchers) -> matchers // define security rules
                        .requestMatchers("/ap1/v1/**") // specifies requests patterns
                )
                .authorizeHttpRequests((authorize) -> authorize // configures request authorization
                        .requestMatchers("/ap1/v1/customer **").hasRole(CUSTOMER.name())
                        .requestMatchers(HttpMethod.GET, "/ap1/v1/customer **").hasAuthority(CUSTOMER_READ.name())
                        .requestMatchers(HttpMethod.POST, "/ap1/v1/customer **").hasAuthority(CUSTOMER_CREATE.name())
                        .requestMatchers(HttpMethod.PUT, "/ap1/v1/customer **").hasAuthority(CUSTOMER_UPDATE.name())
                        .requestMatchers(HttpMethod.DELETE, "/ap1/v1/customer **").hasAuthority(CUSTOMER_DELETE.name())
                        .requestMatchers(HttpMethod.GET, "/ap1/v1/admin **").hasAuthority(ADMIN_READ.name())
                        .anyRequest().hasRole("ADMIN")
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

