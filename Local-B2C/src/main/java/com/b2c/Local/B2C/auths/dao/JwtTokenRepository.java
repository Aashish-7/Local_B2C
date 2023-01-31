package com.b2c.Local.B2C.auths.dao;

import com.b2c.Local.B2C.auths.model.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, UUID> {

    JwtToken findByTokenAndActiveTrue(String token);

    Boolean existsByTokenAndActiveTrue(String token);
}