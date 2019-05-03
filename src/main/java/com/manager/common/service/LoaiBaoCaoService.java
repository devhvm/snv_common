package com.manager.common.service;

import com.manager.common.domain.NhomPhanLoai;
import com.manager.common.repository.NhomPhanLoaiRepository;
import com.manager.common.service.dto.NhomPhanLoaiDTO;
import com.manager.common.service.dto.loaibaocao.LoaiBaoCaoDTO;
import com.manager.common.service.mapper.NhomPhanLoaiMapper;
import com.manager.common.service.mapper.loaibaocao.LoaiBaoCaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NhomPhanLoai.
 */
@Service
@Transactional
public class LoaiBaoCaoService {

    private final Logger log = LoggerFactory.getLogger(LoaiBaoCaoService.class);

    private final NhomPhanLoaiRepository nhomPhanLoaiRepository;

    private final LoaiBaoCaoMapper LoaiBaoCaoMapper;

    public LoaiBaoCaoService(NhomPhanLoaiRepository nhomPhanLoaiRepository, LoaiBaoCaoMapper loaiBaoCaoMapper) {
        this.nhomPhanLoaiRepository = nhomPhanLoaiRepository;
        this.LoaiBaoCaoMapper = loaiBaoCaoMapper;
    }

    /**
     * Get all the nhomPhanLoais.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<LoaiBaoCaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LoaiBaoCaos");
        return nhomPhanLoaiRepository.findAll(pageable)
            .map(LoaiBaoCaoMapper::toDto);
    }


    /**
     * Get one nhomPhanLoai by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<LoaiBaoCaoDTO> findOne(Long id) {
        log.debug("Request to get LoaiBaoCao : {}", id);
        return nhomPhanLoaiRepository.findById(id)
            .map(LoaiBaoCaoMapper::toDto);
    }

}
