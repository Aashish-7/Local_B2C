package com.b2c.Local.B2C.daos;

import com.b2c.Local.B2C.models.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WashingMachineRepository extends JpaRepository<WashingMachine, UUID> {
}