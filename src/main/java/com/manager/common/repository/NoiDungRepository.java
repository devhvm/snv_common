package com.manager.common.repository;

import com.manager.common.domain.NoiDung;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NoiDung entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoiDungRepository extends JpaRepository<NoiDung, Long> {

}
