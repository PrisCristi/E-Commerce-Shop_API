package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
@Table (name = "users")
public class User {
   @Id
   @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
   @NotNull
   private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String role;

    public User(Long id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {

    }
}