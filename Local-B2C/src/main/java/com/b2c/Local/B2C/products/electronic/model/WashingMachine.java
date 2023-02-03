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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "washing_machine")
@Indexed
public class WashingMachine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "washing_machine_id")
    private Long washingMachineId;

    @FullTextField
    private String model;

    @KeywordField
    private String brand;

    private boolean dryer;

    @KeywordField
    private String functionType;

    @GenericField
    private double capacityInKg;

    @GenericField
    private double powerInStar;

    private boolean timer;

    @KeywordField
    private String colour;

    @KeywordField
    private String warranty;

    private boolean digitalDisplay;

    private boolean childLock;

    private boolean shockProof;

    @GenericField
    private double weight;

    private double discountPercentage;

    @KeywordField
    private String availability;

    @JsonIgnore
    private Boolean active;

    @GenericField
    private double price;

    @GenericField
    private int quantity;

    @ManyToOne @JsonIgnore  @IndexedEmbedded
    @JoinColumn(name = "local_store_id", referencedColumnName = "id")
    private LocalStore localStore;

    @Transient
    private String url;

    @PostLoad
    public void postLoad(){
        this.url = "http://localhost:8080/products/washingMachine/"+this.washingMachineId+"/getWashingMachineById";
    }
}
