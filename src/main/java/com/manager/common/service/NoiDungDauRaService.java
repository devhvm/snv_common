package com.manager.common.service;

import com.manager.common.domain.NoiDungDauRa;
import com.manager.common.repository.NoiDungDauRaRepository;
import com.manager.common.service.dto.NoiDungDauRaDTO;
import com.manager.common.service.mapper.NoiDungDauRaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NoiDungDauRa.
 */
@Service
@Transactional
public class NoiDungDauRaService {

    private final Logger log = LoggerFactory.getLogger(NoiDungDauRaService.class);

    private final NoiDungDauRaRepository noiDungDauRaRepository;

    private final NoiDungDauRaMapper noiDungDauRaMapper;

    public NoiDungDauRaService(NoiDungDauRaRepository noiDungDauRaRepository, NoiDungDauRaMapper noiDungDauRaMapper) {
        this.noiDungDauRaRepository = noiDungDauRaRepository;
        this.noiDungDauRaMapper = noiDungDauRaMapper;
    }

    /**
     * Save a noiDungDauRa.
     *
     * @param noiDungDauRaDTO the entity to save
     * @return the persisted entity
     */
    public NoiDungDauRaDTO save(NoiDungDauRaDTO noiDungDauRaDTO) {
        log.debug("Request to save NoiDungDauRa : {}", noiDungDauRaDTO);
        NoiDungDauRa noiDungDauRa = noiDungDauRaMapper.toEntity(noiDungDauRaDTO);
        noiDungDauRa = noiDungDauRaRepository.save(noiDungDauRa);
        return noiDungDauRaMapper.toDto(noiDungDauRa);
    }

    /**
     * Get all the noiDungDauRas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<NoiDungDauRaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoiDungDauRas");
        return noiDungDauRaRepository.findAll(pageable)
            .map(noiDungDauRaMapper::toDto);
    }


    /**
     * Get one noiDungDauRa by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<NoiDungDauRaDTO> findOne(Long id) {
        log.debug("Request to get NoiDungDauRa : {}", id);
        return noiDungDauRaRepository.findById(id)
            .map(noiDungDauRaMapper::toDto);
    }

    /**
     * Delete the noiDungDauRa by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete NoiDungDauRa : {}", id);
        noiDungDauRaRepository.deleteById(id);
    }
}
