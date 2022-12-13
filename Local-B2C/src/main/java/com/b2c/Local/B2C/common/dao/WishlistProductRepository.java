package com.b2c.Local.B2C.common.dao;

import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.common.enums.ProductEnum;
import com.b2c.Local.B2C.common.model.WishlistProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WishlistProductRepository extends JpaRepository<WishlistProduct, UUID> {
    boolean existsByProductIdAndProductAndUserAndDeletedFalse(Long id, ProductEnum productEnum, User user);
}
