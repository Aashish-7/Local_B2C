package com.b2c.Local.B2C.products.electronic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class ElectronicFilterDto {

    private List<String> brand;

    private List<String> model;

    private List<String> colour;

    private List<String> availability;

    private List<Double> price;

    private String city;

    private Integer pinCode;
}
