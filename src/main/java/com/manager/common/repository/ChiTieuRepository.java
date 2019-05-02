package com.manager.common.repository;

import com.manager.common.domain.ChiTieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ChiTieu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChiTieuRepository extends JpaRepository<ChiTieu, Long> {

}
