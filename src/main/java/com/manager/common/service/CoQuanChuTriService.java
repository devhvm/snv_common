package com.manager.common.service;

import com.manager.common.domain.CoQuanChuTri;
import com.manager.common.repository.CoQuanChuTriRepository;
import com.manager.common.service.dto.CoQuanChuTriDTO;
import com.manager.common.service.mapper.CoQuanChuTriMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CoQuanChuTri.
 */
@Service
@Transactional
public class CoQuanChuTriService {

    private final Logger log = LoggerFactory.getLogger(CoQuanChuTriService.class);

    private final CoQuanChuTriRepository coQuanChuTriRepository;

    private final CoQuanChuTriMapper coQuanChuTriMapper;

    public CoQuanChuTriService(CoQuanChuTriRepository coQuanChuTriRepository, CoQuanChuTriMapper coQuanChuTriMapper) {
        this.coQuanChuTriRepository = coQuanChuTriRepository;
        this.coQuanChuTriMapper = coQuanChuTriMapper;
    }

    /**
     * Save a coQuanChuTri.
     *
     * @param coQuanChuTriDTO the entity to save
     * @return the persisted entity
     */
    public CoQuanChuTriDTO save(CoQuanChuTriDTO coQuanChuTriDTO) {
        log.debug("Request to save CoQuanChuTri : {}", coQuanChuTriDTO);
        CoQuanChuTri coQuanChuTri = coQuanChuTriMapper.toEntity(coQuanChuTriDTO);
        coQuanChuTri = coQuanChuTriRepository.save(coQuanChuTri);
        return coQuanChuTriMapper.toDto(coQuanChuTri);
    }

    /**
     * Get all the coQuanChuTris.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CoQuanChuTriDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CoQuanChuTris");
        return coQuanChuTriRepository.findAll(pageable)
            .map(coQuanChuTriMapper::toDto);
    }


    /**
     * Get one coQuanChuTri by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CoQuanChuTriDTO> findOne(Long id) {
        log.debug("Request to get CoQuanChuTri : {}", id);
        return coQuanChuTriRepository.findById(id)
            .map(coQuanChuTriMapper::toDto);
    }

    /**
     * Delete the coQuanChuTri by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CoQuanChuTri : {}", id);
        coQuanChuTriRepository.deleteById(id);
    }
}
