package com.manager.common.repository;

import com.manager.common.domain.DonViTinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DonViTinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DonViTinhRepository extends JpaRepository<DonViTinh, Long> {

}
