package com.b2c.Local.B2C.securities.dao;

import com.b2c.Local.B2C.securities.dto.FilterRequestInfo;
import com.b2c.Local.B2C.securities.model.FilterRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterRequestsRepository extends JpaRepository<FilterRequest, String> {

    /*@Query(nativeQuery = true, value = "select * from filter_request WHERE sessionId=:sessionId ORDER BY localDateTime DESC ")
    List<FilterRequest> getBySessionId(String sessionId);*/


    boolean existsAllBySessionId(String uuid);

    boolean existsAllByUrlAndRequestId(String url, String requestId);

    List<FilterRequest> findAllByUserId(String userId);

    List<FilterRequest> findAllBySessionId(String sessionId);

    List<FilterRequest> findAllByUrlAndRequestId(String url, String requestId);

    List<FilterRequestInfo> getAllByUserId(String userId);
}
