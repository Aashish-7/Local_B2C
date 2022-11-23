package com.b2c.Local.B2C.products.electronic.model;

import com.b2c.Local.B2C.store.model.LocalStore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "refrigerator")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Refrigerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refrigerator_id")
    private Long refrigeratorId;

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

    private Boolean active;

    private double price;

    @ManyToOne
    @JoinColumn(name = "local_store_id", referencedColumnName = "id")
    private LocalStore localStore;
}
