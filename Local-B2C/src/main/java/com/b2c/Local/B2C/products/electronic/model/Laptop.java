package com.b2c.Local.B2C.products.electronic.model;

import com.b2c.Local.B2C.store.model.LocalStore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "laptop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Laptop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private Long laptopId;

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

    private String colour;

    private double discountPercentage;

    private int noOfSpeaker;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "local_store_id", referencedColumnName = "id")
    private LocalStore localStore;
}
