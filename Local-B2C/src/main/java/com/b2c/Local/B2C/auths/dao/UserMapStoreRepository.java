package com.b2c.Local.B2C.auths.dao;

import com.b2c.Local.B2C.auths.model.UserMapStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapStoreRepository extends JpaRepository<UserMapStore, String> {
}