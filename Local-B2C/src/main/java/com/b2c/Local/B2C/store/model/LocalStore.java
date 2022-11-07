package com.b2c.Local.B2C.store.model;

import com.b2c.Local.B2C.auths.model.User;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@TypeDefs(@TypeDef(name = "list-array", typeClass = ListArrayType.class))
@Entity
@Table(name = "local_store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalStore {

    @Id
    @Column(name = "id")
    private UUID Id = UUID.randomUUID();

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

    @Column(columnDefinition = "text[]")
    @Type(type = "list-array")
    private List<String> listOfProduct;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
