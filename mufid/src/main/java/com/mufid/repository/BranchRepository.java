package com.mufid.repository;

import com.mufid.entity.bean.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    @Query("SELECT b FROM Branch b WHERE b.isActive = true AND b.isDeleted = false")
    List<Branch> findAllActive();

    @Query("SELECT b FROM Branch b WHERE b.isActive = true AND b.isDeleted = false")
    Page<Branch> findAllActive(Pageable pageable);

    @Query("SELECT b FROM Branch b WHERE b.id = :id AND b.isActive = true AND b.isDeleted = false")
    Optional<Branch> findActiveById(@Param("id") Integer id);

    @Query("SELECT b FROM Branch b WHERE b.code = :code AND b.isActive = true AND b.isDeleted = false")
    Optional<Branch> findActiveByCode(@Param("code") String code);

    @Query("SELECT b FROM Branch b WHERE b.province.id = :provinceId " +
            "AND b.isActive = true AND b.isDeleted = false")
    List<Branch> findActiveByProvinceId(@Param("provinceId") Integer provinceId);

    @Query("SELECT b FROM Branch b WHERE b.province.id = :provinceId " +
            "AND b.isActive = true AND b.isDeleted = false")
    Page<Branch> findActiveByProvinceId(@Param("provinceId") Integer provinceId, Pageable pageable);

    @Query("SELECT b FROM Branch b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND b.isActive = true AND b.isDeleted = false")
    List<Branch> findActiveByNameContaining(@Param("name") String name);

    @Query("SELECT b FROM Branch b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND b.isActive = true AND b.isDeleted = false")
    Page<Branch> findActiveByNameContaining(@Param("name") String name, Pageable pageable);

    boolean existsByCodeAndIsDeletedFalse(String code);
}
