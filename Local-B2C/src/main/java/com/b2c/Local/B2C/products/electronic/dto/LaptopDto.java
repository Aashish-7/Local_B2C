package com.b2c.Local.B2C.products.electronic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.b2c.Local.B2C.products.electronic.model.Laptop} entity
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LaptopDto implements Serializable {
    private String model;
    private String brand;
    private double price;
    private double weightInKg;
    private int screenSizeInInch;
    private boolean touchScreen;
    private String availability;
    private String screenResolution;
    private String cpuBrand;
    private String cpuModel;
    private String cpuGeneration;
    private String cpuClockSpeed;
    private int noOfCpuCore;
    private String hardDiskSize;
    private String ramSize;
    private String ramType;
    private boolean fingerprint;
    private String os;
    private String warranty;
    private String batteryBackupHour;
    private String graphicCard;
    private int noOfUsbPorts;
    private boolean ethernetPort;
    private boolean bluetooth;
    private boolean headphoneJack;
    private boolean hdmiPort;
    private String color;
    private double discountPercentage;
    private int noOfSpeaker;
    private UUID localStoreId;
}