package com.manager.common.service.impl;

import com.manager.common.service.PhamViService;
import com.manager.common.domain.PhamVi;
import com.manager.common.repository.PhamViRepository;
import com.manager.common.service.dto.PhamViDTO;
import com.manager.common.service.mapper.PhamViMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing PhamVi.
 */
@Service
@Transactional
public class PhamViServiceImpl implements PhamViService {

    private final Logger log = LoggerFactory.getLogger(PhamViServiceImpl.class);

    private final PhamViRepository phamViRepository;

    private final PhamViMapper phamViMapper;

    public PhamViServiceImpl(PhamViRepository phamViRepository, PhamViMapper phamViMapper) {
        this.phamViRepository = phamViRepository;
        this.phamViMapper = phamViMapper;
    }

    /**
     * Save a phamVi.
     *
     * @param phamViDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PhamViDTO save(PhamViDTO phamViDTO) {
        log.debug("Request to save PhamVi : {}", phamViDTO);
        PhamVi phamVi = phamViMapper.toEntity(phamViDTO);
        phamVi = phamViRepository.save(phamVi);
        return phamViMapper.toDto(phamVi);
    }

    /**
     * Get all the phamVis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PhamViDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PhamVis");
        return phamViRepository.findAll(pageable)
            .map(phamViMapper::toDto);
    }



    /**
     *  get all the phamVis where Donvi is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<PhamViDTO> findAllWhereDonviIsNull() {
        log.debug("Request to get all phamVis where Donvi is null");
        return StreamSupport
            .stream(phamViRepository.findAll().spliterator(), false)
            .filter(phamVi -> phamVi.getDonvi() == null)
            .map(phamViMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one phamVi by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PhamViDTO> findOne(Long id) {
        log.debug("Request to get PhamVi : {}", id);
        return phamViRepository.findById(id)
            .map(phamViMapper::toDto);
    }

    /**
     * Delete the phamVi by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PhamVi : {}", id);
        phamViRepository.deleteById(id);
    }
}
