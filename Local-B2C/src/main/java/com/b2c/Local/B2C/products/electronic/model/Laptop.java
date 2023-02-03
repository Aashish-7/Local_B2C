package com.b2c.Local.B2C.products.electronic.model;

import com.b2c.Local.B2C.store.model.LocalStore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "laptop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Indexed
public class Laptop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private Long laptopId;

    @FullTextField
    private String model;

    @KeywordField
    private String brand;

    @GenericField
    private double price;

    @GenericField
    private double weightInKg;

    @GenericField
    private int screenSizeInInch;

    @GenericField
    private boolean touchScreen;

    @KeywordField
    private String availability;

    private String screenResolution;

    @KeywordField
    private String cpuBrand;

    @KeywordField
    private String cpuModel;

    private String cpuGeneration;

    private String cpuClockSpeed;

    private int noOfCpuCore;

    private String hardDiskSize;

    @KeywordField
    private String ramSize;

    @KeywordField
    private String ramType;

    private boolean fingerprint;

    @KeywordField
    private String os;

    @KeywordField
    private String warranty;

    private String batteryBackupHour;

    @FullTextField
    private String graphicCard;

    private int noOfUsbPorts;

    private boolean ethernetPort;

    private boolean bluetooth;

    private boolean headphoneJack;

    private boolean hdmiPort;

    @KeywordField
    private String colour;

    private double discountPercentage;

    private int noOfSpeaker;

    @JsonIgnore
    private Boolean active;

    @GenericField
    private int quantity;

    @ManyToOne @JsonIgnore  @IndexedEmbedded
    @JoinColumn(name = "local_store_id", referencedColumnName = "id")
    private LocalStore localStore;

    @Transient
    private String url;

    @PostLoad
    public void postLoad(){
        this.url = "http://localhost:8080/products/laptop/"+this.laptopId+"/getLaptopById";
    }
}
