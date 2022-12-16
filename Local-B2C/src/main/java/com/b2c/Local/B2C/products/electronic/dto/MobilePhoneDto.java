package com.b2c.Local.B2C.products.electronic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.b2c.Local.B2C.products.electronic.model.MobilePhone} entity
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MobilePhoneDto implements Serializable {
    private String model;
    private String brand;
    private String networkConnectivity;
    private String simType;
    private String displayType;
    private String displayResolution;
    private String displaySize;
    private String os;
    private String brandUi;
    private String chipset;
    private String cpuCore;
    private String cpuClockSpeed;
    private String gpu;
    private boolean memoryCordSlotSupported;
    private String internalMemorySize;
    private int mainCameraCount;
    private String mainCameraSpecs;
    private boolean frontCamera;
    private String frontCameraSpecs;
    private String batterySize;
    private String chargingType;
    private String chargerOutput;
    private String color;
    private int weightInGrams;
    private double price;
    private boolean bluetooth;
    private boolean gps;
    private boolean nfc;
    private boolean radio;
    private String usbType;
    private boolean audioJack;
    private boolean wlan;
    private String availability;
    private double discountPercentage;
    private String warranty;
    private UUID localStoreId;
}