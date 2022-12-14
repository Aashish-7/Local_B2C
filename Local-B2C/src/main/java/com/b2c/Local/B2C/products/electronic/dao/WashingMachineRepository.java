package com.b2c.Local.B2C.products.electronic.dao;

import com.b2c.Local.B2C.products.electronic.model.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WashingMachineRepository extends JpaRepository<WashingMachine, Long> {

    List<WashingMachine> findByLocalStore_IdAndActiveTrue(UUID id);

    boolean existsByWashingMachineIdAndActiveTrue(Long washingMachineId);

    WashingMachine findByWashingMachineIdAndActiveTrue(Long washingMachineId);

    List<WashingMachine> findAllByBrandLikeAndActiveTrue(String brand);

    List<WashingMachine> findAllByModelLikeAndActiveTrue(String model);

    List<WashingMachine> findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(String model, int pinCode);

    List<WashingMachine> findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(String Brand, int pinCode);

    @Query("select distinct a.model from WashingMachine a where a.active =true")
    List<String> findAllDistinctModel();

    @Query("select distinct  a.brand from WashingMachine a where a.active =true")
    List<String> findAllDistinctBrand();

    @Query("select distinct a.colour from WashingMachine a where a.active =true")
    List<String> findAllDistinctColour();

    @Query("select distinct  a.price from WashingMachine a where a.active =true")
    List<Double> findAllDistinctPrice();

    @Query("select distinct a.availability from WashingMachine a where a.active =true")
    List<String> findAllDistinctAvailability();

    List<WashingMachine> findByActiveTrueAndLocalStore_IdAndLocalStore_ActiveTrue(UUID uuid);
}