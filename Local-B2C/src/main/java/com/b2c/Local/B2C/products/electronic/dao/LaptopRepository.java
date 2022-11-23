package com.b2c.Local.B2C.products.electronic.dao;

import com.b2c.Local.B2C.products.electronic.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    List<Laptop> findByLocalStore_IdAndActiveTrue(UUID id);

    boolean existsByLaptopIdAndActiveTrue(Long laptopId);

    Laptop findByLaptopIdAndActiveTrue(Long laptopId);

    List<Laptop> findAllByBrandLikeAndActiveTrue(String brand);

    List<Laptop> findAllByModelLikeAndActiveTrue(String model);

    List<Laptop> findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(String model, int pinCode);

    List<Laptop> findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(String Brand, int pinCode);

    @Query("select distinct a.model from Laptop a where a.active =true")
    List<String> findAllDistinctModel();

    @Query("select distinct  a.brand from Laptop a where a.active =true")
    List<String> findAllDistinctBrand();

    @Query("select distinct a.colour from Laptop a where a.active =true")
    List<String> findAllDistinctColour();

    @Query("select distinct  a.price from Laptop a where a.active =true")
    List<Double> findAllDistinctPrice();

    @Query("select distinct a.availability from Laptop a where a.active =true")
    List<String> findAllDistinctAvailability();
}