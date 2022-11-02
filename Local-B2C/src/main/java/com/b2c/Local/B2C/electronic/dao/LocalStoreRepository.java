package com.b2c.Local.B2C.electronic.dao;

import com.b2c.Local.B2C.electronic.model.LocalStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalStoreRepository extends JpaRepository<LocalStore, Long> {
}