package com.b2c.Local.B2C.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProductEnum {


    AC("AC"),
    LAPTOP("Laptop"),
    MOBILEPHONE("MobilePhone"),
    REFRIGERATOR("Refrigerator"),
    TELEVISION("Television"),
    WASHINGMACHINE("WashingMachine");

    @Getter
    private final String value;

}
