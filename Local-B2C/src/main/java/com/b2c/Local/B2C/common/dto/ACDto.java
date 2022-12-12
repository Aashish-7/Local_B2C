package com.b2c.Local.B2C.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ACDto {

    private Long acId;
    private String model;
    private String brand;
    private String colour;
    private String warranty;
    private boolean digitalDisplay;
    private double weightInKg;
    private double discountPercentage;
    private double powerInStar;
    private String availability;
    private boolean builtInStabilizer;
    private double capacityInTon;
    private String mode;
    private boolean timer;
    private boolean wiFi;
    private String airConditionerType;
    private double price;
}