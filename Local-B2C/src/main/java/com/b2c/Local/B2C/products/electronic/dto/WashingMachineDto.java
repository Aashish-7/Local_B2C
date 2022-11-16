package com.b2c.Local.B2C.products.electronic.dto;

import com.b2c.Local.B2C.products.electronic.model.WashingMachine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link WashingMachine} entity
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class WashingMachineDto implements Serializable {
    private String model;
    private String brand;
    private boolean dryer;
    private String functionType;
    private double capacityInKg;
    private double powerInStar;
    private boolean timer;
    private String colour;
    private String warranty;
    private boolean digitalDisplay;
    private boolean childLock;
    private boolean shockProof;
    private double weight;
    private double discountPercentage;
    private String availability;
    private UUID localStoreId;
    private double price;
}