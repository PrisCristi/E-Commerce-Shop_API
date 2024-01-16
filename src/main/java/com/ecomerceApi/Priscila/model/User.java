package com.ecomerceApi.Priscila.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "users")
@NoArgsConstructor
public class User {

   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
   @NotNull
   private String userName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String role;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn (name = "role_id", referencedColumnName = "id"))
    private List<Role> roles= new ArrayList<>();

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(Long id, String name, String email, String password, String role) {
        this.id = id;
        this.userName = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}