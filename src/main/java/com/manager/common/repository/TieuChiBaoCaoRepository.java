package com.manager.common.repository;

import com.manager.common.domain.TieuChiBaoCao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TieuChiBaoCao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TieuChiBaoCaoRepository extends JpaRepository<TieuChiBaoCao, Long> {

}
