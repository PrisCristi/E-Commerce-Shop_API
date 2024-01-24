package com.ecomerceApi.Priscila.config;

import com.ecomerceApi.Priscila.EUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static com.ecomerceApi.Priscila.model.Permission.*;
import static com.ecomerceApi.Priscila.model.Role.CUSTOMER;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    EUserDetailsService userDetailsService;


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
        http
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
                        .anyRequest().hasRole("ADMIN")
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Manages user sessions
                .authenticationProvider(authenticationProvider()) // Manages user authentication
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
