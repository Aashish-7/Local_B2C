package com.b2c.Local.B2C.products.electronic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.b2c.Local.B2C.products.electronic.model.Refrigerator} entity
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RefrigeratorDto implements Serializable {
    private String model;
    private String brand;
    private String colour;
    private String warranty;
    private boolean digitalDisplay;
    private double weight;
    private double discountPercentage;
    private double powerInStar;
    private double capacityInLitre;
    private boolean multiDoor;
    private String freezerPosition;
    private String availability;
    private boolean builtInStabilizer;
    private UUID localStoreId;
    private double price;
}