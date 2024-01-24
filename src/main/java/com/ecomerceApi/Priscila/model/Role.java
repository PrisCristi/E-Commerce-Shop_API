package com.ecomerceApi.Priscila.model;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {
    CUSTOMER(Set.of(

            Permission.CUSTOMER_READ,
            Permission.CUSTOMER_UPDATE,
            Permission.CUSTOMER_DELETE,
            Permission.CUSTOMER_CREATE
    )),

    ADMIN(Set.of(
            Permission.ADMIN_CREATE,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_READ,
            Permission.ADMIN_DELETE,

            Permission.CUSTOMER_CREATE,
            Permission.CUSTOMER_UPDATE,
            Permission.CUSTOMER_READ,
            Permission.CUSTOMER_DELETE

    ));



    private final Set<Permission> permissions; // Created a set to not have duplications

    public List<SimpleGrantedAuthority> getAuthorities() {  // Generate list of user with permission
        var authorities = getPermissions()
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect (Collectors.toList());
        authorities.add (new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
