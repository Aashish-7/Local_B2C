package com.b2c.Local.B2C.products.electronic.model;

import com.b2c.Local.B2C.store.model.LocalStore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "television")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Television {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "television_id")
    private Long televisionId;

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

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "local_store_id", referencedColumnName = "id")
    private LocalStore localStore;
}
