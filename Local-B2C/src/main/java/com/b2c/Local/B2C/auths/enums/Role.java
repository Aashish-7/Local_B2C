package com.b2c.Local.B2C.auths.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Role {

    ADMIN("admin"),
    STORE_OWNER("storeOwner"),
    USER("user");


    @Getter
    private final String name;
}
