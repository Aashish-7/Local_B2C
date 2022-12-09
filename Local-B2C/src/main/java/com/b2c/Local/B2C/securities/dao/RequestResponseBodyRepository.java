package com.b2c.Local.B2C.securities.dao;

import com.b2c.Local.B2C.securities.model.RequestResponseBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestResponseBodyRepository extends JpaRepository<RequestResponseBody, String> {

    RequestResponseBody findByFilterRequest_RequestId(String id);

}