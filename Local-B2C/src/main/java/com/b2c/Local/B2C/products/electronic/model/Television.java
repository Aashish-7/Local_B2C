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
@Table(name = "television")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Indexed
public class Television implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "television_id")
    private Long televisionId;

    @FullTextField
    private String model;

    @KeywordField
    private String brand;

    @GenericField
    private double price;

    @KeywordField
    private String availability;

    @KeywordField
    private String displayType;

    @GenericField
    private int displaySizeInInch;

    private String screenResolution;

    @GenericField
    private int noOfHdmiPorts;

    private int noOfUsbPorts;

    private String features;

    @GenericField
    private boolean wiFi;

    @GenericField
    private boolean ethernet;

    @GenericField
    private double ramSizeGb;

    @GenericField
    private double memorySizeGb;

    private String displayRefreshRate;

    private int noOfCpuCore;

    @GenericField
    private int noOfSpeakers;

    @KeywordField
    private String colour;

    private double discountPercentage;

    @KeywordField
    private String warranty;

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
        this.url = "http://localhost:8080/products/television/"+this.televisionId+"/getTelevisionById";
    }
}
