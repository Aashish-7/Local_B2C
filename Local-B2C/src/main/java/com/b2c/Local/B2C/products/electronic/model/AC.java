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
@Table(name = "ac")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Indexed
public class AC implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ac_id")
    private Long acId;

    @FullTextField
    private String model;

    @FullTextField
    private String brand;

    @FullTextField
    private String colour;

    @FullTextField
    private String warranty;

    private boolean digitalDisplay;

    private double weightInKg;

    private double discountPercentage;

    @GenericField
    private double powerInStar;

    @FullTextField
    private String availability;

    private boolean builtInStabilizer;

    @GenericField
    private double capacityInTon;

    private String mode;

    private boolean timer;

    private boolean wiFi;

    private String airConditionerType;

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
        this.url = "http://localhost:8080/products/ac/"+this.acId+"/getAcById";
    }
}
