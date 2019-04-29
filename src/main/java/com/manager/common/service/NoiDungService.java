package com.manager.common.service;

import com.manager.common.domain.NoiDung;
import com.manager.common.repository.NoiDungRepository;
import com.manager.common.service.dto.NoiDungDTO;
import com.manager.common.service.mapper.NoiDungMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NoiDung.
 */
@Service
@Transactional
public class NoiDungService {

    private final Logger log = LoggerFactory.getLogger(NoiDungService.class);

    private final NoiDungRepository noiDungRepository;

    private final NoiDungMapper noiDungMapper;

    public NoiDungService(NoiDungRepository noiDungRepository, NoiDungMapper noiDungMapper) {
        this.noiDungRepository = noiDungRepository;
        this.noiDungMapper = noiDungMapper;
    }

    /**
     * Save a noiDung.
     *
     * @param noiDungDTO the entity to save
     * @return the persisted entity
     */
    public NoiDungDTO save(NoiDungDTO noiDungDTO) {
        log.debug("Request to save NoiDung : {}", noiDungDTO);
        NoiDung noiDung = noiDungMapper.toEntity(noiDungDTO);
        noiDung = noiDungRepository.save(noiDung);
        return noiDungMapper.toDto(noiDung);
    }

    /**
     * Get all the noiDungs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<NoiDungDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoiDungs");
        return noiDungRepository.findAll(pageable)
            .map(noiDungMapper::toDto);
    }


    /**
     * Get one noiDung by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<NoiDungDTO> findOne(Long id) {
        log.debug("Request to get NoiDung : {}", id);
        return noiDungRepository.findById(id)
            .map(noiDungMapper::toDto);
    }

    /**
     * Delete the noiDung by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete NoiDung : {}", id);
        noiDungRepository.deleteById(id);
    }
}
