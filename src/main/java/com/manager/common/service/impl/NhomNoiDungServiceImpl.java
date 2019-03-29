package com.manager.common.service.impl;

import com.manager.common.service.NhomNoiDungService;
import com.manager.common.domain.NhomNoiDung;
import com.manager.common.repository.NhomNoiDungRepository;
import com.manager.common.service.dto.NhomNoiDungDTO;
import com.manager.common.service.mapper.NhomNoiDungMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NhomNoiDung.
 */
@Service
@Transactional
public class NhomNoiDungServiceImpl implements NhomNoiDungService {

    private final Logger log = LoggerFactory.getLogger(NhomNoiDungServiceImpl.class);

    private final NhomNoiDungRepository nhomNoiDungRepository;

    private final NhomNoiDungMapper nhomNoiDungMapper;

    public NhomNoiDungServiceImpl(NhomNoiDungRepository nhomNoiDungRepository, NhomNoiDungMapper nhomNoiDungMapper) {
        this.nhomNoiDungRepository = nhomNoiDungRepository;
        this.nhomNoiDungMapper = nhomNoiDungMapper;
    }

    /**
     * Save a nhomNoiDung.
     *
     * @param nhomNoiDungDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NhomNoiDungDTO save(NhomNoiDungDTO nhomNoiDungDTO) {
        log.debug("Request to save NhomNoiDung : {}", nhomNoiDungDTO);
        NhomNoiDung nhomNoiDung = nhomNoiDungMapper.toEntity(nhomNoiDungDTO);
        nhomNoiDung = nhomNoiDungRepository.save(nhomNoiDung);
        return nhomNoiDungMapper.toDto(nhomNoiDung);
    }

    /**
     * Get all the nhomNoiDungs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NhomNoiDungDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NhomNoiDungs");
        return nhomNoiDungRepository.findAll(pageable)
            .map(nhomNoiDungMapper::toDto);
    }


    /**
     * Get one nhomNoiDung by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NhomNoiDungDTO> findOne(Long id) {
        log.debug("Request to get NhomNoiDung : {}", id);
        return nhomNoiDungRepository.findById(id)
            .map(nhomNoiDungMapper::toDto);
    }

    /**
     * Delete the nhomNoiDung by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NhomNoiDung : {}", id);        nhomNoiDungRepository.deleteById(id);
    }
}
