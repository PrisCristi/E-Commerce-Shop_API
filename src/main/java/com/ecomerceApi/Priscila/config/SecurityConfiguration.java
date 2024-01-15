package com.ecomerceApi.Priscila.config;

import com.ecomerceApi.Priscila.model.Permission;
import com.ecomerceApi.Priscila.service.UserService;
import com.mysql.cj.protocol.AuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ecomerceApi.Priscila.model.Permission.*;
import static com.ecomerceApi.Priscila.model.Role.ADMIN;
import static com.ecomerceApi.Priscila.model.Role.CUSTOMER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserService userService;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter, UserService userService, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userService = userService;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/ap1/v1/auth **")
                .permitAll()
                .requestMatchers("/ap1/v1/admin **").hasAnyRole(ADMIN.name())

                .requestMatchers(HttpMethod.GET, "/ap1/v1/admin **").hasAnyAuthority(ADMIN_READ.name(), CUSTOMER_READ.name())
                .requestMatchers(HttpMethod.POST, "/ap1/v1/admin **").hasAnyAuthority(ADMIN_CREATE.name(),CUSTOMER_CREATE.name())
                .requestMatchers(HttpMethod.PUT, "/ap1/v1/admin **").hasAnyAuthority(ADMIN_UPDATE.name(), CUSTOMER_UPDATE.name())
                .requestMatchers(HttpMethod.DELETE, "/ap1/v1/admin **").hasAnyAuthority(ADMIN_DELETE.name(), CUSTOMER_DELETE.name())

                .requestMatchers("/ap1/v1/customer **").hasRole(CUSTOMER.name())

                .requestMatchers(HttpMethod.GET, "/ap1/v1/customer **").hasAuthority(CUSTOMER_READ.name())
                .requestMatchers(HttpMethod.POST, "/ap1/v1/customer **").hasAuthority(CUSTOMER_CREATE.name())
                .requestMatchers(HttpMethod.PUT, "/ap1/v1/customer **").hasAuthority(CUSTOMER_UPDATE.name())
                .requestMatchers(HttpMethod.DELETE, "/ap1/v1/customer **").hasAuthority(CUSTOMER_DELETE.name())


                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider((org.springframework.security.authentication.AuthenticationProvider) authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
