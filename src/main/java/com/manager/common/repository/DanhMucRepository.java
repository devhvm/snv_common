package com.manager.common.repository;

import com.manager.common.domain.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DanhMuc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, Long> {

}
