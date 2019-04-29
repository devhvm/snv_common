package com.manager.common.service;

import com.manager.common.domain.DanhMuc;
import com.manager.common.repository.DanhMucRepository;
import com.manager.common.service.dto.DanhMucDTO;
import com.manager.common.service.mapper.DanhMucMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DanhMuc.
 */
@Service
@Transactional
public class DanhMucService {

    private final Logger log = LoggerFactory.getLogger(DanhMucService.class);

    private final DanhMucRepository danhMucRepository;

    private final DanhMucMapper danhMucMapper;

    public DanhMucService(DanhMucRepository danhMucRepository, DanhMucMapper danhMucMapper) {
        this.danhMucRepository = danhMucRepository;
        this.danhMucMapper = danhMucMapper;
    }

    /**
     * Save a danhMuc.
     *
     * @param danhMucDTO the entity to save
     * @return the persisted entity
     */
    public DanhMucDTO save(DanhMucDTO danhMucDTO) {
        log.debug("Request to save DanhMuc : {}", danhMucDTO);
        DanhMuc danhMuc = danhMucMapper.toEntity(danhMucDTO);
        danhMuc = danhMucRepository.save(danhMuc);
        return danhMucMapper.toDto(danhMuc);
    }

    /**
     * Get all the danhMucs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<DanhMucDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanhMucs");
        return danhMucRepository.findAll(pageable)
            .map(danhMucMapper::toDto);
    }


    /**
     * Get one danhMuc by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucDTO> findOne(Long id) {
        log.debug("Request to get DanhMuc : {}", id);
        return danhMucRepository.findById(id)
            .map(danhMucMapper::toDto);
    }

    /**
     * Delete the danhMuc by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete DanhMuc : {}", id);
        danhMucRepository.deleteById(id);
    }
}
