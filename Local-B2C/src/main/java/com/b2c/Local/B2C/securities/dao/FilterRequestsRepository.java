package com.b2c.Local.B2C.securities.dao;

import com.b2c.Local.B2C.securities.dto.FilterRequestInfo;
import com.b2c.Local.B2C.securities.model.FilterRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterRequestsRepository extends JpaRepository<FilterRequest, String> {

    /*@Query(nativeQuery = true, value = "select * from filter_request WHERE sessionId=:sessionId ORDER BY localDateTime DESC ")
    List<FilterRequest> getBySessionId(String sessionId);*/


    boolean existsAllBySessionId(String uuid);

    boolean existsAllByUrlAndSessionId(String url, String sessionId);

    @Cacheable(cacheNames = "findAllByUserId", key = "#userId")
    List<FilterRequest> findAllByUserId(String userId);

    List<FilterRequest> findAllBySessionId(String sessionId);

    List<FilterRequest> findAllByUrlAndSessionId(String url, String sessionId);

    @Cacheable(cacheNames = "getAllByUserId", key = "#userId")
    List<FilterRequestInfo> getAllByUserId(String userId);
}
