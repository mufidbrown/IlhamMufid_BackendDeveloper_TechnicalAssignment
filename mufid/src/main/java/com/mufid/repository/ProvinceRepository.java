package com.mufid.repository;

import com.mufid.entity.bean.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query("SELECT p FROM Province p WHERE p.isActive = true AND p.isDeleted = false")
    List<Province> findAllActive();

    @Query("SELECT p FROM Province p WHERE p.isActive = true AND p.isDeleted = false")
    Page<Province> findAllActive(Pageable pageable);

    @Query("SELECT p FROM Province p WHERE p.id = :id AND p.isActive = true AND p.isDeleted = false")
    Optional<Province> findActiveById(@Param("id") Integer id);

    @Query("SELECT p FROM Province p WHERE p.code = :code AND p.isActive = true AND p.isDeleted = false")
    Optional<Province> findActiveByCode(@Param("code") String code);

    @Query("SELECT p FROM Province p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND p.isActive = true AND p.isDeleted = false")
    List<Province> findActiveByNameContaining(@Param("name") String name);

    @Query("SELECT p FROM Province p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND p.isActive = true AND p.isDeleted = false")
    Page<Province> findActiveByNameContaining(@Param("name") String name, Pageable pageable);

    boolean existsByCodeAndIsDeletedFalse(String code);
}
