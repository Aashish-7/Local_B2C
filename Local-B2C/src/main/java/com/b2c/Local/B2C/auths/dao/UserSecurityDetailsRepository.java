package com.b2c.Local.B2C.auths.dao;

import com.b2c.Local.B2C.auths.model.UserSecurityDetails;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@CacheConfig
public interface UserSecurityDetailsRepository extends JpaRepository<UserSecurityDetails, UUID> {

    UserSecurityDetails findByUserEmail(String email);
}