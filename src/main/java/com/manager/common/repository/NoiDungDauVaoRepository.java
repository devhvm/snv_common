package com.manager.common.repository;

import com.manager.common.domain.NoiDungDauVao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NoiDungDauVao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoiDungDauVaoRepository extends JpaRepository<NoiDungDauVao, Long> {

}
