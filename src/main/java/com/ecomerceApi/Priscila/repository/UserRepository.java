package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);

    Boolean existsByUsername (String username);

    Boolean existsByEmail(String email);
}

