package com.manager.common.service;

import com.manager.common.domain.TieuChi;
import com.manager.common.repository.TieuChiRepository;
import com.manager.common.service.dto.TieuChiDTO;
import com.manager.common.service.dto.coquanchutri.TieuChiDetailDTO;
import com.manager.common.service.mapper.mauphathanh.TieuChiDetailMapper;
import com.manager.common.service.mapper.TieuChiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing TieuChi.
 */
@Service
@Transactional
public class TieuChiService {

    private final Logger log = LoggerFactory.getLogger(TieuChiService.class);

    private final TieuChiRepository tieuChiRepository;

    private final TieuChiMapper tieuChiMapper;

    private final TieuChiDetailMapper tieuChiDetailMapper;

    public TieuChiService(TieuChiRepository tieuChiRepository, TieuChiMapper tieuChiMapper, TieuChiDetailMapper tieuChiDetailMapper) {
        this.tieuChiRepository = tieuChiRepository;
        this.tieuChiMapper = tieuChiMapper;
        this.tieuChiDetailMapper = tieuChiDetailMapper;
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



    /**
     * Get all the tieuChis.
     *
     * @param id the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TieuChiDetailDTO> findByCoQuanChuTriId(Long id) {
        log.debug("Request to get all TieuChis");
        return tieuChiRepository.findAllByCoQuanChuTriId(id).stream()
            .map(tieuChiDetailMapper::toDto).collect(Collectors.toList());
    }

}
