package com.b2c.Local.B2C.securities.dao;

import com.b2c.Local.B2C.securities.model.FilterRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilterRequestRepository extends JpaRepository<FilterRequest, UUID> {
}
