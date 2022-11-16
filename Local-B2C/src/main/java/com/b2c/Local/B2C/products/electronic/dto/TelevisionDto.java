package com.b2c.Local.B2C.products.electronic.dto;

import com.b2c.Local.B2C.products.electronic.model.Television;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link Television} entity
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TelevisionDto implements Serializable {
    private String model;
    private String brand;
    private double price;
    private String availability;
    private String displayType;
    private int displaySizeInInch;
    private String screenResolution;
    private int noOfHdmiPorts;
    private int noOfUsbPorts;
    private String features;
    private boolean wiFi;
    private boolean ethernet;
    private double ramSizeGb;
    private double memorySizeGb;
    private String displayRefreshRate;
    private int noOfCpuCore;
    private int noOfSpeakers;
    private String color;
    private double discountPercentage;
    private String warranty;
    private UUID localStoreId;
}