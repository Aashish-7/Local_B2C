package com.b2c.Local.B2C.store.dao;

import com.b2c.Local.B2C.store.model.LocalStore;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
@CacheConfig(cacheNames = {"LocalStoreRepository"})
public interface LocalStoreRepository extends JpaRepository<LocalStore, UUID> {

    @Cacheable(value = "findByUserIdAndActiveTrue", key = "#userId")
    List<LocalStore> findByUserIdAndActiveTrue(UUID userId);

    @Cacheable(cacheNames = "findByPinCodeAndActiveTrue", key = "#pinCode")
    List<LocalStore> findByPinCodeAndActiveTrue(int pinCode);

    @Cacheable(cacheNames = "findByCityAndActiveTrue", key = "#city")
    List<LocalStore> findByCityAndActiveTrue(String city);

    @Cacheable(cacheNames = "existsByIdAndActiveTrue", key = "#id")
    boolean existsByIdAndActiveTrue(UUID id);

    Iterable<? extends LocalStore> findByUserId(UUID loggedInUserId);
}