package com.manager.common.repository;

import com.manager.common.domain.KyCongBo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the KyCongBo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KyCongBoRepository extends JpaRepository<KyCongBo, Long> {

}
