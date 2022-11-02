package com.b2c.Local.B2C.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "washing_machine", schema = "public")
public class WashingMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "washing_machine_id")
    private UUID washingMachineId;

    private String model;

    private String brand;

    private boolean dryer;

    private String functionType;

    private double capacityInKg;

    private double powerInStar;

    private boolean timer;

    private String colour;

    private String warranty;

    private boolean digitalDisplay;

    private boolean childLock;

    private boolean shockProof;

    private double weight;

    private double discountPercentage;

    private String availability;

    @ManyToOne(targetEntity = LocalStore.class)
//    @JsonBackReference
    @JoinColumn(name = "store", referencedColumnName = "local_store_id")
    private LocalStore store;
}
