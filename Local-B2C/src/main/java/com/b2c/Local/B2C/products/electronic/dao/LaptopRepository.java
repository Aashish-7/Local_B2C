package com.b2c.Local.B2C.products.electronic.dao;

import com.b2c.Local.B2C.products.electronic.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
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
}