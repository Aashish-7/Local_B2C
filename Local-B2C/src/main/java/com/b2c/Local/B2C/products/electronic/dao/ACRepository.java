package com.b2c.Local.B2C.products.electronic.dao;

import com.b2c.Local.B2C.products.electronic.model.AC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ACRepository extends JpaRepository<AC, Long> {
}