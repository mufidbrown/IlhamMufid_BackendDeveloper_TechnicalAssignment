package com.mufid.repository;


import com.mufid.entity.bean.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Query("SELECT s FROM Store s WHERE s.isActive = true AND s.isDeleted = false")
    List<Store> findAllActive();

    @Query("SELECT s FROM Store s WHERE s.isActive = true AND s.isDeleted = false")
    Page<Store> findAllActive(Pageable pageable);

    @Query("SELECT s FROM Store s WHERE s.id = :id AND s.isActive = true AND s.isDeleted = false")
    Optional<Store> findActiveById(@Param("id") Integer id);

    @Query("SELECT s FROM Store s WHERE s.code = :code AND s.isActive = true AND s.isDeleted = false")
    Optional<Store> findActiveByCode(@Param("code") String code);

    @Query("SELECT s FROM Store s WHERE s.branch.id = :branchId " +
            "AND s.isActive = true AND s.isDeleted = false")
    List<Store> findActiveByBranchId(@Param("branchId") Integer branchId);

    @Query("SELECT s FROM Store s WHERE s.branch.id = :branchId " +
            "AND s.isActive = true AND s.isDeleted = false")
    Page<Store> findActiveByBranchId(@Param("branchId") Integer branchId, Pageable pageable);

    @Query("SELECT s FROM Store s JOIN s.branch b JOIN b.province p " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :provinceName, '%')) " +
            "AND s.isActive = true AND s.isDeleted = false " +
            "AND b.isActive = true AND b.isDeleted = false " +
            "AND p.isActive = true AND p.isDeleted = false")
    List<Store> findActiveByProvinceName(@Param("provinceName") String provinceName);

    @Query("SELECT s FROM Store s JOIN s.branch b JOIN b.province p " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :provinceName, '%')) " +
            "AND s.isActive = true AND s.isDeleted = false " +
            "AND b.isActive = true AND b.isDeleted = false " +
            "AND p.isActive = true AND p.isDeleted = false")
    Page<Store> findActiveByProvinceName(@Param("provinceName") String provinceName, Pageable pageable);

    @Query("SELECT s FROM Store s WHERE s.isWhitelist = true " +
            "AND s.isActive = true AND s.isDeleted = false")
    List<Store> findAllWhitelistStores();

    @Query("SELECT s FROM Store s WHERE s.isWhitelist = true " +
            "AND s.isActive = true AND s.isDeleted = false")
    Page<Store> findAllWhitelistStores(Pageable pageable);

    @Query("SELECT s FROM Store s JOIN s.branch b JOIN b.province p " +
            "WHERE (LOWER(p.name) LIKE LOWER(CONCAT('%', :provinceName, '%')) OR s.isWhitelist = true) " +
            "AND s.isActive = true AND s.isDeleted = false " +
            "AND b.isActive = true AND b.isDeleted = false " +
            "AND p.isActive = true AND p.isDeleted = false")
    List<Store> findActiveByProvinceNameIncludingWhitelist(@Param("provinceName") String provinceName);

    @Query("SELECT s FROM Store s JOIN s.branch b JOIN b.province p " +
            "WHERE (LOWER(p.name) LIKE LOWER(CONCAT('%', :provinceName, '%')) OR s.isWhitelist = true) " +
            "AND s.isActive = true AND s.isDeleted = false " +
            "AND b.isActive = true AND b.isDeleted = false " +
            "AND p.isActive = true AND p.isDeleted = false")
    Page<Store> findActiveByProvinceNameIncludingWhitelist(@Param("provinceName") String provinceName, Pageable pageable);

    @Query("SELECT DISTINCT s FROM Store s JOIN s.branch b JOIN b.province p " +
            "WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND s.isActive = true AND s.isDeleted = false " +
            "AND b.isActive = true AND b.isDeleted = false " +
            "AND p.isActive = true AND p.isDeleted = false")
    List<Store> findActiveByNameContaining(@Param("name") String name);

    @Query("SELECT DISTINCT s FROM Store s JOIN s.branch b JOIN b.province p " +
            "WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND s.isActive = true AND s.isDeleted = false " +
            "AND b.isActive = true AND b.isDeleted = false " +
            "AND p.isActive = true AND p.isDeleted = false")
    Page<Store> findActiveByNameContaining(@Param("name") String name, Pageable pageable);

    boolean existsByCodeAndIsDeletedFalse(String code);
}
