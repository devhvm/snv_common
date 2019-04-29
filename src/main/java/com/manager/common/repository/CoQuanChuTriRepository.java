package com.manager.common.repository;

import com.manager.common.domain.CoQuanChuTri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CoQuanChuTri entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoQuanChuTriRepository extends JpaRepository<CoQuanChuTri, Long> {

}
