package com.b2c.Local.B2C.products.electronic.model;

import com.b2c.Local.B2C.store.model.LocalStore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mobile_phone")
public class MobilePhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mobile_phone_id")
    private Long mobilePhoneId;

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

    private String colour;

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

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "local_store_id", referencedColumnName = "id")
    private LocalStore localStore;
}
