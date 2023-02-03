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
@Table(name = "refrigerator")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Indexed
public class Refrigerator implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refrigerator_id")
    private Long refrigeratorId;

    @FullTextField
    private String model;

    @KeywordField
    private String brand;

    @KeywordField
    private String colour;

    @KeywordField
    private String warranty;

    private boolean digitalDisplay;

    @GenericField
    private double weight;

    private double discountPercentage;

    @GenericField
    private double powerInStar;

    @GenericField
    private double capacityInLitre;

    private boolean multiDoor;

    @KeywordField
    private String freezerPosition;

    @KeywordField
    private String availability;

    @GenericField
    private boolean builtInStabilizer;

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
        this.url = "http://localhost:8080/products/refrigerator/"+this.refrigeratorId+"/getRefrigeratorById";
    }
}
