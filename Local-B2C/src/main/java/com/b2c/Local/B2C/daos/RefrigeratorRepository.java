package com.b2c.Local.B2C.daos;

import com.b2c.Local.B2C.models.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, UUID> {
}