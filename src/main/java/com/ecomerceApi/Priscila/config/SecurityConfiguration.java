package com.ecomerceApi.Priscila.config;

import com.ecomerceApi.Priscila.EUserDetailsService;
import com.ecomerceApi.Priscila.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ecomerceApi.Priscila.model.Permission.*;
import static com.ecomerceApi.Priscila.model.Role.ADMIN;
import static com.ecomerceApi.Priscila.model.Role.CUSTOMER;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    JwtAuthEntryPoint authEntryPoint;
    @Autowired
    EUserDetailsService userDetailsService;

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http){
    http
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint()
}




/*

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/ap1/v1/auth **")
                .permitAll()
                .requestMatchers("/ap1/v1/admin **").hasAnyRole(ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/ap1/v1/admin **").hasAnyAuthority(ADMIN_READ.name(), CUSTOMER_READ.name())
                .requestMatchers(HttpMethod.POST, "/ap1/v1/admin **").hasAnyAuthority(ADMIN_CREATE.name(), CUSTOMER_CREATE.name())
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
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
