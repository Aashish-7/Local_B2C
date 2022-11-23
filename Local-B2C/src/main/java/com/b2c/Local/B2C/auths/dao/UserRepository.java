package com.b2c.Local.B2C.auths.dao;

import com.b2c.Local.B2C.auths.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Cacheable(cacheNames = "findByEmail", key = "#email")
    User findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByIdAndIsActiveTrue(UUID id);
}