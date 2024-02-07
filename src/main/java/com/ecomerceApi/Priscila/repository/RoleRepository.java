package com.ecomerceApi.Priscila.repository;

import com.ecomerceApi.Priscila.model.ERole;
import com.ecomerceApi.Priscila.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName (ERole name);



}