package com.ecomerceApi.Priscila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PriscilaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriscilaApplication.class, args);

    }
}
