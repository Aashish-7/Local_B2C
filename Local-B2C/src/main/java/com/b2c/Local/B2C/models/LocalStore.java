package com.b2c.Local.B2C.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.*;
import java.util.UUID;

//@TypeDefs(@TypeDef(name = "list-array", typeClass = ListArrayType.class))
@Entity
@Table(name = "local_store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EnableScheduling
public class LocalStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "local_store_id")
    private UUID localStoreId;

    private String storeName;

    private String storeAddress;

    private int pinCode;

    private String city;

    private String email;

    private double contactNo;

    private double alternateContactNo;

    private String addressLink;

    private String ownerName;

    private String description;
//
//    @Column(columnDefinition = "text[]")
//    @Type(type = "list-array")
//    private List<String> listOfProduct;
}
