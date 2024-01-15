package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // Long because our Id is type long.
    UserDetails findByEmail(String email);
    Boolean existsByEmail (String email);

}
