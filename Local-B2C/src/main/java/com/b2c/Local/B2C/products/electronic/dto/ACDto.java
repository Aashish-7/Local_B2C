package com.b2c.Local.B2C.products.electronic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.b2c.Local.B2C.products.electronic.model.AC} entity
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ACDto implements Serializable {
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
    private UUID localStoreId;
}