package com.b2c.Local.B2C.products.electronic.dao;

import com.b2c.Local.B2C.products.electronic.model.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WashingMachineRepository extends JpaRepository<WashingMachine, Long> {

    List<WashingMachine> findByLocalStore_IdAndActiveTrue(UUID id);
}