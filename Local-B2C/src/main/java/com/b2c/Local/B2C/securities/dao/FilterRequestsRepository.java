package com.b2c.Local.B2C.securities.dao;

import com.b2c.Local.B2C.securities.model.FilterRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Repository
public interface FilterRequestsRepository extends JpaRepository<FilterRequest, UUID> {
}
