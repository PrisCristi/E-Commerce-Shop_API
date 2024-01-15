package com.ecomerceApi.Priscila.model;

import java.util.Collections;
import java.util.Set;

public enum Role {
    USER(Collections.emptySet()),
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


    private final Set<Permission> permissions; // to no have duplications set is better.

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
