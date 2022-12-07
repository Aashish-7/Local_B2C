package com.b2c.Local.B2C.products.electronic.dao;

import com.b2c.Local.B2C.products.electronic.model.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface TelevisionRepository extends JpaRepository<Television, Long> {

    List<Television> findByLocalStore_IdAndActiveTrue(UUID id);

    boolean existsByTelevisionIdAndActiveTrue(Long televisionId);

    Television findByTelevisionIdAndActiveTrue(Long televisionId);

    List<Television> findAllByModelLikeAndActiveTrue(String model);

    List<Television> findAllByBrandLikeAndActiveTrue(String brand);

    List<Television> findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(String model, int pinCode);

    List<Television> findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(String Brand, int pinCode);

    @Query("select distinct a.model from Television a where a.active =true")
    List<String> findAllDistinctModel();

    @Query("select distinct  a.brand from Television a where a.active =true")
    List<String> findAllDistinctBrand();

    @Query("select distinct a.colour from Television a where a.active =true")
    List<String> findAllDistinctColour();

    @Query("select distinct  a.price from Television a where a.active =true")
    List<Double> findAllDistinctPrice();

    @Query("select distinct a.availability from Television a where a.active =true")
    List<String> findAllDistinctAvailability();

    List<Television> findByActiveTrueAndLocalStore_IdAndLocalStore_ActiveTrue(UUID uuid);
}