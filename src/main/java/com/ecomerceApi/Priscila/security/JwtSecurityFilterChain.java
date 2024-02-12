/*
package com.ecomerceApi.Priscila.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class JwtSecurityFilterChain {

    public AuthorizationTokenFilter authenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // protect against fake solicitations
                .authorizeHttpRequests((authorize) -> authorize // configures request authorization
                                .requestMatchers("/api/v1/auth/*").permitAll()
                                .anyRequest().authenticated()

                      /* .requestMatchers("/ap1/v1/customer **").hasRole(CUSTOMER.name())
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
*/

