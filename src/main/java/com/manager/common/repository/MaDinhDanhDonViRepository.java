package com.manager.common.repository;

import com.manager.common.domain.MaDinhDanhDonVi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MaDinhDanhDonVi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MaDinhDanhDonViRepository extends JpaRepository<MaDinhDanhDonVi, Long> {

}
