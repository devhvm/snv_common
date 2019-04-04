package com.manager.common.repository;

import com.manager.common.domain.NhomPhanLoai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NhomPhanLoai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhomPhanLoaiRepository extends JpaRepository<NhomPhanLoai, Long> {

}
