package com.manager.common.repository;

import com.manager.common.domain.NhomChiTieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NhomChiTieu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhomChiTieuRepository extends JpaRepository<NhomChiTieu, Long> {

}
