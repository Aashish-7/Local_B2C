package com.b2c.Local.B2C.auths.dao;

import com.b2c.Local.B2C.auths.model.UserSecurityDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
public interface UserSecurityDetailsRepository extends JpaRepository<UserSecurityDetails, UUID> {

    UserSecurityDetails findByUserEmail(String email);
}