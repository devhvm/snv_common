package com.manager.common.service.impl;

import com.manager.common.service.DonViService;
import com.manager.common.domain.DonVi;
import com.manager.common.repository.DonViRepository;
import com.manager.common.service.dto.DonViDTO;
import com.manager.common.service.mapper.DonViMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DonVi.
 */
@Service
@Transactional
public class DonViServiceImpl implements DonViService {

    private final Logger log = LoggerFactory.getLogger(DonViServiceImpl.class);

    private final DonViRepository donViRepository;

    private final DonViMapper donViMapper;

    public DonViServiceImpl(DonViRepository donViRepository, DonViMapper donViMapper) {
        this.donViRepository = donViRepository;
        this.donViMapper = donViMapper;
    }

    /**
     * Save a donVi.
     *
     * @param donViDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DonViDTO save(DonViDTO donViDTO) {
        log.debug("Request to save DonVi : {}", donViDTO);
        DonVi donVi = donViMapper.toEntity(donViDTO);
        donVi = donViRepository.save(donVi);
        return donViMapper.toDto(donVi);
    }

    /**
     * Get all the donVis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DonViDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DonVis");
        return donViRepository.findAll(pageable)
            .map(donViMapper::toDto);
    }


    /**
     * Get one donVi by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DonViDTO> findOne(Long id) {
        log.debug("Request to get DonVi : {}", id);
        return donViRepository.findById(id)
            .map(donViMapper::toDto);
    }

    /**
     * Delete the donVi by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DonVi : {}", id);        donViRepository.deleteById(id);
    }
}
