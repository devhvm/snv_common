package com.manager.common.service;

import com.manager.common.domain.TieuChi;
import com.manager.common.repository.TieuChiRepository;
import com.manager.common.service.dto.TieuChiDTO;
import com.manager.common.service.mapper.TieuChiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TieuChi.
 */
@Service
@Transactional
public class TieuChiService {

    private final Logger log = LoggerFactory.getLogger(TieuChiService.class);

    private final TieuChiRepository tieuChiRepository;

    private final TieuChiMapper tieuChiMapper;

    public TieuChiService(TieuChiRepository tieuChiRepository, TieuChiMapper tieuChiMapper) {
        this.tieuChiRepository = tieuChiRepository;
        this.tieuChiMapper = tieuChiMapper;
    }

    /**
     * Save a tieuChi.
     *
     * @param tieuChiDTO the entity to save
     * @return the persisted entity
     */
    public TieuChiDTO save(TieuChiDTO tieuChiDTO) {
        log.debug("Request to save TieuChi : {}", tieuChiDTO);
        TieuChi tieuChi = tieuChiMapper.toEntity(tieuChiDTO);
        tieuChi = tieuChiRepository.save(tieuChi);
        return tieuChiMapper.toDto(tieuChi);
    }

    /**
     * Get all the tieuChis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<TieuChiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TieuChis");
        return tieuChiRepository.findAll(pageable)
            .map(tieuChiMapper::toDto);
    }


    /**
     * Get one tieuChi by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TieuChiDTO> findOne(Long id) {
        log.debug("Request to get TieuChi : {}", id);
        return tieuChiRepository.findById(id)
            .map(tieuChiMapper::toDto);
    }

    /**
     * Delete the tieuChi by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete TieuChi : {}", id);
        tieuChiRepository.deleteById(id);
    }
}
