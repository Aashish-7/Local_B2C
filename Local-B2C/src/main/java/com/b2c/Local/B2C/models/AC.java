package com.b2c.Local.B2C.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ac")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EnableScheduling
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
}
