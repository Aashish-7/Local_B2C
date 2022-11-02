package com.b2c.Local.B2C.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ac", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ac_id")
    private UUID acId;

    private String model;

    private String brand;

    private String colour;

    private String warranty;

    private boolean digitalDisplay;

    private double weightInKg;

    private double discountPercentage;

    private double powerInStar;

    private String availability;

    private boolean builtInStabilizer;

    private double capacityInTon;

    private String mode;

    private boolean timer;

    private boolean wiFi;

    private String airConditionerType;

    @ManyToOne(targetEntity = LocalStore.class)
//    @JsonBackReference
//    @JoinColumn(name = "store", referencedColumnName = "local_store_id")
    @JoinColumn(name = "store", referencedColumnName = "local_store_id")
    private LocalStore store;
}
