package com.b2c.Local.B2C.products.electronic.dao;

import com.b2c.Local.B2C.products.electronic.model.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface TelevisionRepository extends JpaRepository<Television, Long> {

    List<Television> findByLocalStore_IdAndActiveTrue(UUID id);
}