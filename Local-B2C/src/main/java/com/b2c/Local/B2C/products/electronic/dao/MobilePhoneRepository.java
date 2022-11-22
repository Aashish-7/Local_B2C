package com.b2c.Local.B2C.products.electronic.dao;

import com.b2c.Local.B2C.products.electronic.model.MobilePhone;
import org.springframework.data.jpa.repository.JpaRepository;
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
}