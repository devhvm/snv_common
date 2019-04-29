package com.manager.common.repository;

import com.manager.common.domain.NhomDanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NhomDanhMuc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhomDanhMucRepository extends JpaRepository<NhomDanhMuc, Long> {

}
