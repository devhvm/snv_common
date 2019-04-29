package com.manager.common.repository;

import com.manager.common.domain.NoiDungDauRa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NoiDungDauRa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoiDungDauRaRepository extends JpaRepository<NoiDungDauRa, Long> {

}
