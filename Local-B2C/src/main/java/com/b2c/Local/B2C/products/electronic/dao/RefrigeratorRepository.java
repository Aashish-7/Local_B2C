package com.b2c.Local.B2C.products.electronic.dao;

import com.b2c.Local.B2C.products.electronic.model.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {

    List<Refrigerator> findByLocalStore_IdAndActiveTrue(UUID id);
}