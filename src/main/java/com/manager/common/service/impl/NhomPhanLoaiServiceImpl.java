package com.manager.common.service.impl;

import com.manager.common.service.NhomPhanLoaiService;
import com.manager.common.domain.NhomPhanLoai;
import com.manager.common.repository.NhomPhanLoaiRepository;
import com.manager.common.service.dto.NhomPhanLoaiDTO;
import com.manager.common.service.mapper.NhomPhanLoaiMapper;
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
public class NhomPhanLoaiServiceImpl implements NhomPhanLoaiService {

    private final Logger log = LoggerFactory.getLogger(NhomPhanLoaiServiceImpl.class);

    private final NhomPhanLoaiRepository nhomPhanLoaiRepository;

    private final NhomPhanLoaiMapper nhomPhanLoaiMapper;

    public NhomPhanLoaiServiceImpl(NhomPhanLoaiRepository nhomPhanLoaiRepository, NhomPhanLoaiMapper nhomPhanLoaiMapper) {
        this.nhomPhanLoaiRepository = nhomPhanLoaiRepository;
        this.nhomPhanLoaiMapper = nhomPhanLoaiMapper;
    }

    /**
     * Save a nhomPhanLoai.
     *
     * @param nhomPhanLoaiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NhomPhanLoaiDTO save(NhomPhanLoaiDTO nhomPhanLoaiDTO) {
        log.debug("Request to save NhomPhanLoai : {}", nhomPhanLoaiDTO);
        NhomPhanLoai nhomPhanLoai = nhomPhanLoaiMapper.toEntity(nhomPhanLoaiDTO);
        nhomPhanLoai = nhomPhanLoaiRepository.save(nhomPhanLoai);
        return nhomPhanLoaiMapper.toDto(nhomPhanLoai);
    }

    /**
     * Get all the nhomPhanLoais.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NhomPhanLoaiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NhomPhanLoais");
        return nhomPhanLoaiRepository.findAll(pageable)
            .map(nhomPhanLoaiMapper::toDto);
    }


    /**
     * Get one nhomPhanLoai by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NhomPhanLoaiDTO> findOne(Long id) {
        log.debug("Request to get NhomPhanLoai : {}", id);
        return nhomPhanLoaiRepository.findById(id)
            .map(nhomPhanLoaiMapper::toDto);
    }

    /**
     * Delete the nhomPhanLoai by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NhomPhanLoai : {}", id);        nhomPhanLoaiRepository.deleteById(id);
    }
}
