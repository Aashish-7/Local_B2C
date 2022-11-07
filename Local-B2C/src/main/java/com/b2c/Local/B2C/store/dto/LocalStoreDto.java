package com.b2c.Local.B2C.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * A DTO for the {@link com.b2c.Local.B2C.store.model.LocalStore} entity
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LocalStoreDto implements Serializable {

    @Size(min = 5, max = 100)
    private  String storeName;

    @Size(min = 10)
    private  String storeAddress;

    @Min(100000) @Max(999999)
    private  int pinCode;

    @Size(min = 1)
    private  String city;

    @Email
    private  String email;

    @Range(min = 6000000000L, max = 9999999999L)
    private long contactNo;

    @Range(min = 6000000000L, max = 9999999999L)
    private  long alternateContactNo;

    @URL
    private  String addressLink;

    @Size(min = 5, max = 100)
    private  String ownerName;

    @Size(min = 5)
    private  String description;

    @Size(min = 5)
    private  List<String> listOfProduct;

    @NotNull
    private UUID userId;
}