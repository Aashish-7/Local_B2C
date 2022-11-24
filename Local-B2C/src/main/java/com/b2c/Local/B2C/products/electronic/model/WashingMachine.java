package com.b2c.Local.B2C.products.electronic.model;

import com.b2c.Local.B2C.store.model.LocalStore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "washing_machine")
public class WashingMachine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "washing_machine_id")
    private Long washingMachineId;

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

    private Boolean active;

    private double price;

    @ManyToOne
    @JoinColumn(name = "local_store_id", referencedColumnName = "id")
    private LocalStore localStore;
}
