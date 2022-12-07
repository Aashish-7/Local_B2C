package com.b2c.Local.B2C.products.electronic.dao;

import com.b2c.Local.B2C.products.electronic.model.MobilePhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface MobilePhoneRepository extends JpaRepository<MobilePhone, Long> {

    List<MobilePhone> findByLocalStore_IdAndActiveTrue(UUID id);

    boolean existsByMobilePhoneIdAndActiveTrue(Long mobilePhoneId);

    MobilePhone findByMobilePhoneIdAndActiveTrue(Long mobilePhoneId);

    List<MobilePhone> findAllByModelLikeAndActiveTrue(String model);

    List<MobilePhone> findAllByBrandLikeAndActiveTrue(String brand);

    List<MobilePhone> findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(String model, int pinCode);

    List<MobilePhone> findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(String Brand, int pinCode);

    @Query("select distinct a.model from MobilePhone a where a.active =true")
    List<String> findAllDistinctModel();

    @Query("select distinct  a.brand from MobilePhone a where a.active =true")
    List<String> findAllDistinctBrand();

    @Query("select distinct a.colour from MobilePhone a where a.active =true")
    List<String> findAllDistinctColour();

    @Query("select distinct  a.price from MobilePhone a where a.active =true")
    List<Double> findAllDistinctPrice();

    @Query("select distinct a.availability from MobilePhone a where a.active =true")
    List<String> findAllDistinctAvailability();

    List<MobilePhone> findByActiveTrueAndLocalStore_IdAndLocalStore_ActiveTrue(UUID uuid);
}