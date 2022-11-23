package com.b2c.Local.B2C.products.electronic.dao;

import com.b2c.Local.B2C.products.electronic.model.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {

    List<Refrigerator> findByLocalStore_IdAndActiveTrue(UUID id);

    boolean existsByRefrigeratorIdAndActiveTrue(Long refrigeratorId);

    Refrigerator findByRefrigeratorIdAndActiveTrue(Long refrigeratorId);

    List<Refrigerator> findAllByModelLikeAndActiveTrue(String model);

    List<Refrigerator> findAllByBrandLikeAndActiveTrue(String brand);

    List<Refrigerator> findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(String model, int pinCode);

    List<Refrigerator> findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(String Brand, int pinCode);

    @Query("select distinct a.model from Refrigerator a where a.active =true")
    List<String> findAllDistinctModel();

    @Query("select distinct  a.brand from Refrigerator a where a.active =true")
    List<String> findAllDistinctBrand();

    @Query("select distinct a.colour from Refrigerator a where a.active =true")
    List<String> findAllDistinctColour();

    @Query("select distinct  a.price from Refrigerator a where a.active =true")
    List<Double> findAllDistinctPrice();

    @Query("select distinct a.availability from Refrigerator a where a.active =true")
    List<String> findAllDistinctAvailability();
}