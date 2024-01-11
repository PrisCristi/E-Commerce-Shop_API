package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Boolean emailAvailable(String email);
    Optional<User>findByEmail(String email);

}
