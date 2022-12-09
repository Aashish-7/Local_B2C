package com.b2c.Local.B2C.securities.dao;

import com.b2c.Local.B2C.securities.model.RequestResponseBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface RequestResponseBodyRepository extends JpaRepository<RequestResponseBody, String> {

    boolean existsById(String id);

    @Modifying @Transactional
    @Query(value = "update request_response_body  set responseBody=:responseBody where id=:id",nativeQuery = true)
    Integer updateById(@Param("id") String id, @Param("responseBody") Object responseBody);

}