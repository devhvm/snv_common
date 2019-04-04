package com.manager.common.service;

import com.manager.common.domain.NhomDanhMuc;
import com.manager.common.repository.NhomDanhMucRepository;
import com.manager.common.service.dto.NhomDanhMucDTO;
import com.manager.common.service.mapper.NhomDanhMucMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NhomDanhMuc.
 */
@Service
@Transactional
public class NhomDanhMucService {

    private final Logger log = LoggerFactory.getLogger(NhomDanhMucService.class);

    private final NhomDanhMucRepository nhomDanhMucRepository;

    private final NhomDanhMucMapper nhomDanhMucMapper;

    public NhomDanhMucService(NhomDanhMucRepository nhomDanhMucRepository, NhomDanhMucMapper nhomDanhMucMapper) {
        this.nhomDanhMucRepository = nhomDanhMucRepository;
        this.nhomDanhMucMapper = nhomDanhMucMapper;
    }

    /**
     * Save a nhomDanhMuc.
     *
     * @param nhomDanhMucDTO the entity to save
     * @return the persisted entity
     */
    public NhomDanhMucDTO save(NhomDanhMucDTO nhomDanhMucDTO) {
        log.debug("Request to save NhomDanhMuc : {}", nhomDanhMucDTO);
        NhomDanhMuc nhomDanhMuc = nhomDanhMucMapper.toEntity(nhomDanhMucDTO);
        nhomDanhMuc = nhomDanhMucRepository.save(nhomDanhMuc);
        return nhomDanhMucMapper.toDto(nhomDanhMuc);
    }

    /**
     * Get all the nhomDanhMucs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<NhomDanhMucDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NhomDanhMucs");
        return nhomDanhMucRepository.findAll(pageable)
            .map(nhomDanhMucMapper::toDto);
    }


    /**
     * Get one nhomDanhMuc by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<NhomDanhMucDTO> findOne(Long id) {
        log.debug("Request to get NhomDanhMuc : {}", id);
        return nhomDanhMucRepository.findById(id)
            .map(nhomDanhMucMapper::toDto);
    }

    /**
     * Delete the nhomDanhMuc by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete NhomDanhMuc : {}", id);
        nhomDanhMucRepository.deleteById(id);
    }
}
