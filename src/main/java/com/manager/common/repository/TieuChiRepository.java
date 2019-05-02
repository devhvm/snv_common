package com.manager.common.repository;

import com.manager.common.domain.CoQuanChuTri;
import com.manager.common.domain.TieuChi;
import com.manager.common.domain.enumeration.Status;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the TieuChi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TieuChiRepository extends JpaRepository<TieuChi, Long> {
    List<TieuChi> findAllByStatus(Status status);
    List<TieuChi> findAllByCoQuanChuTriId(Long coQuanChuTriId);
}
