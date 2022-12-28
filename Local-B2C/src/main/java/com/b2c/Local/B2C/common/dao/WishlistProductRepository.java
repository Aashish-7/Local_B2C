package com.b2c.Local.B2C.common.dao;

import com.b2c.Local.B2C.auths.model.User;
import com.b2c.Local.B2C.common.enums.ProductEnum;
import com.b2c.Local.B2C.common.model.WishlistProduct;
import com.b2c.Local.B2C.common.model.WishlistProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WishlistProductRepository extends JpaRepository<WishlistProduct, UUID> {
    boolean existsByProductIdAndProductAndUserAndDeletedFalse(Long id, ProductEnum productEnum, User user);

    boolean existsAllByDeletedFalseAndUser_Id(UUID uuid);

    List<WishlistProduct> findAllByDeletedFalseAndUser_Id(UUID uuid);

    boolean existsByIdAndDeletedFalse(UUID wishlistId);

    long countDistinctByDeletedFalseAndProductAndProductIdIn(ProductEnum product, List<Long> productId);

    @Query(nativeQuery = true , value = "SELECT COUNT(DISTINCT (productid)) count FROM wishlist_product WHERE product = :product AND productid In(:productId) AND deleted is False")
    Long getProductCountFromWishlistProduct(String product, List<Long> productId);

    @Query(nativeQuery = true, value = "SELECT productid, count(productid) count  From wishlist_product where product = :product group by productid having count(productid)>1 order by count DESC  LIMIT 1")
    WishlistProductProjection getProductIdCount(String product);
}
