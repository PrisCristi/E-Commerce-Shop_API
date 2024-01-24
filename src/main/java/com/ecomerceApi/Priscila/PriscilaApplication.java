package com.ecomerceApi.Priscila;

import com.ecomerceApi.Priscila.auth.AuthenticationService;
import com.ecomerceApi.Priscila.auth.RegisterRequest;
import com.ecomerceApi.Priscila.model.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PriscilaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriscilaApplication.class, args);

    }

    public CommandLineRunner commandLineRunner(AuthenticationService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                var admin = RegisterRequest.builder()
                        .name("Admin")
                        .email("admin@email.com")
                        .password("password")
                        .role(Role.ADMIN)
                        .build();
                System.out.println("Admin token: " + service.register(admin).getToken());

                var customer = RegisterRequest.builder()
                        .name("Customer")
                        .email("custumer@email.com")
                        .password("password")
                        .role(Role.CUSTOMER)
                        .build();
                System.out.println("Customer token: " + service.register(customer).getToken());
            }
        };
    }
}
