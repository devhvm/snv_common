package com.manager.common.service.impl;

import com.manager.common.service.NhomChiTieuService;
import com.manager.common.domain.NhomChiTieu;
import com.manager.common.repository.NhomChiTieuRepository;
import com.manager.common.service.dto.NhomChiTieuDTO;
import com.manager.common.service.mapper.NhomChiTieuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NhomChiTieu.
 */
@Service
@Transactional
public class NhomChiTieuServiceImpl implements NhomChiTieuService {

    private final Logger log = LoggerFactory.getLogger(NhomChiTieuServiceImpl.class);

    private final NhomChiTieuRepository nhomChiTieuRepository;

    private final NhomChiTieuMapper nhomChiTieuMapper;

    public NhomChiTieuServiceImpl(NhomChiTieuRepository nhomChiTieuRepository, NhomChiTieuMapper nhomChiTieuMapper) {
        this.nhomChiTieuRepository = nhomChiTieuRepository;
        this.nhomChiTieuMapper = nhomChiTieuMapper;
    }

    /**
     * Save a nhomChiTieu.
     *
     * @param nhomChiTieuDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NhomChiTieuDTO save(NhomChiTieuDTO nhomChiTieuDTO) {
        log.debug("Request to save NhomChiTieu : {}", nhomChiTieuDTO);
        NhomChiTieu nhomChiTieu = nhomChiTieuMapper.toEntity(nhomChiTieuDTO);
        nhomChiTieu = nhomChiTieuRepository.save(nhomChiTieu);
        return nhomChiTieuMapper.toDto(nhomChiTieu);
    }

    /**
     * Get all the nhomChiTieus.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NhomChiTieuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NhomChiTieus");
        return nhomChiTieuRepository.findAll(pageable)
            .map(nhomChiTieuMapper::toDto);
    }


    /**
     * Get one nhomChiTieu by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NhomChiTieuDTO> findOne(Long id) {
        log.debug("Request to get NhomChiTieu : {}", id);
        return nhomChiTieuRepository.findById(id)
            .map(nhomChiTieuMapper::toDto);
    }

    /**
     * Delete the nhomChiTieu by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NhomChiTieu : {}", id);        nhomChiTieuRepository.deleteById(id);
    }
}
