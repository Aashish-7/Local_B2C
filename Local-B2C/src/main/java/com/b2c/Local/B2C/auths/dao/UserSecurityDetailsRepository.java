package com.b2c.Local.B2C.auths.dao;

import com.b2c.Local.B2C.auths.model.UserSecurityDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserSecurityDetailsRepository extends JpaRepository<UserSecurityDetails, UUID> {

    UserSecurityDetails findByUserEmail(String email);
}