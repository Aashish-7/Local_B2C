package com.b2c.Local.B2C.daos;

import com.b2c.Local.B2C.models.LocalStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocalStoreRepository extends JpaRepository<LocalStore, UUID> {
}