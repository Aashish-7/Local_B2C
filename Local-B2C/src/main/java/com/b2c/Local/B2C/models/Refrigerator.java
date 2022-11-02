package com.b2c.Local.B2C.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "refrigerator", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Refrigerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refrigerator_id")
    private UUID refrigeratorId;

    private String model;

    private String brand;

    private String colour;

    private String warranty;

    private boolean digitalDisplay;

    private double weight;

    private double discountPercentage;

    private double powerInStar;

    private double capacityInLitre;

    private boolean multiDoor;

    private String freezerPosition;

    private String availability;

    private boolean builtInStabilizer;

    @ManyToOne(targetEntity = LocalStore.class)
//    @JsonBackReference
//    @JoinColumn(name = "store", referencedColumnName = "local_store_id")
    @JoinColumn(name = "store", referencedColumnName = "local_store_id")
    private LocalStore store;}
