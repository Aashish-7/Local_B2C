package com.b2c.Local.B2C.products.electronic.dao;

import com.b2c.Local.B2C.products.electronic.model.AC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ACRepository extends JpaRepository<AC, Long> {

    List<AC> findByLocalStore_IdAndActiveTrue(UUID id);

    @Query("select a from AC a where a.localStore.id =: id  and a.active = true")
    List<AC> getAllAcByStoreId(UUID id);

    boolean existsByActiveTrue();

    boolean existsByAcIdAndActiveTrue(Long acId);

    AC findByAcIdAndActiveTrue(Long acId);

    List<AC> findAllByModelLikeAndActiveTrue(String model);

    List<AC> findAllByBrandLikeAndActiveTrue(String brand);


}