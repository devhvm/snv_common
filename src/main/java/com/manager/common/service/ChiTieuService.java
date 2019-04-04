package com.manager.common.service;

import com.manager.common.domain.ChiTieu;
import com.manager.common.repository.ChiTieuRepository;
import com.manager.common.service.dto.ChiTieuDTO;
import com.manager.common.service.mapper.ChiTieuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ChiTieu.
 */
@Service
@Transactional
public class ChiTieuService {

    private final Logger log = LoggerFactory.getLogger(ChiTieuService.class);

    private final ChiTieuRepository chiTieuRepository;

    private final ChiTieuMapper chiTieuMapper;

    public ChiTieuService(ChiTieuRepository chiTieuRepository, ChiTieuMapper chiTieuMapper) {
        this.chiTieuRepository = chiTieuRepository;
        this.chiTieuMapper = chiTieuMapper;
    }

    /**
     * Save a chiTieu.
     *
     * @param chiTieuDTO the entity to save
     * @return the persisted entity
     */
    public ChiTieuDTO save(ChiTieuDTO chiTieuDTO) {
        log.debug("Request to save ChiTieu : {}", chiTieuDTO);
        ChiTieu chiTieu = chiTieuMapper.toEntity(chiTieuDTO);
        chiTieu = chiTieuRepository.save(chiTieu);
        return chiTieuMapper.toDto(chiTieu);
    }

    /**
     * Get all the chiTieus.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ChiTieuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ChiTieus");
        return chiTieuRepository.findAll(pageable)
            .map(chiTieuMapper::toDto);
    }


    /**
     * Get one chiTieu by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ChiTieuDTO> findOne(Long id) {
        log.debug("Request to get ChiTieu : {}", id);
        return chiTieuRepository.findById(id)
            .map(chiTieuMapper::toDto);
    }

    /**
     * Delete the chiTieu by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ChiTieu : {}", id);
        chiTieuRepository.deleteById(id);
    }
}
