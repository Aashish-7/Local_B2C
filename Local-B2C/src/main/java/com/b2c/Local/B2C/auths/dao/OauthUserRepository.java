package com.b2c.Local.B2C.auths.dao;

import com.b2c.Local.B2C.auths.model.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthUserRepository extends JpaRepository<OauthUser, String> {

    OauthUser findByAccessToken(String token);

    Boolean existsByAccessToken(String token);
}