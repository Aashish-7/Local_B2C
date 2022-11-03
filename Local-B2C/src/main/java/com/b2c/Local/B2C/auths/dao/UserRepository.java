package com.b2c.Local.B2C.auths.dao;

import com.b2c.Local.B2C.auths.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);
}