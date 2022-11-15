package com.b2c.Local.B2C.products.electronic.model;

import com.b2c.Local.B2C.store.model.LocalStore;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.*;


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
    private Long acId;

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

    private Boolean active;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "local_store_id", referencedColumnName = "id")
    private LocalStore localStore;
}
