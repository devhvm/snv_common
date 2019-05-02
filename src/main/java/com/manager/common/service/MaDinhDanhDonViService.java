package com.manager.common.service;

import com.manager.common.domain.MaDinhDanhDonVi;
import com.manager.common.repository.MaDinhDanhDonViRepository;
import com.manager.common.service.dto.MaDinhDanhDonViDTO;
import com.manager.common.service.mapper.MaDinhDanhDonViMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MaDinhDanhDonVi.
 */
@Service
@Transactional
public class MaDinhDanhDonViService {

    private final Logger log = LoggerFactory.getLogger(MaDinhDanhDonViService.class);

    private final MaDinhDanhDonViRepository maDinhDanhDonViRepository;

    private final MaDinhDanhDonViMapper maDinhDanhDonViMapper;

    public MaDinhDanhDonViService(MaDinhDanhDonViRepository maDinhDanhDonViRepository, MaDinhDanhDonViMapper maDinhDanhDonViMapper) {
        this.maDinhDanhDonViRepository = maDinhDanhDonViRepository;
        this.maDinhDanhDonViMapper = maDinhDanhDonViMapper;
    }

    /**
     * Save a maDinhDanhDonVi.
     *
     * @param maDinhDanhDonViDTO the entity to save
     * @return the persisted entity
     */
    public MaDinhDanhDonViDTO save(MaDinhDanhDonViDTO maDinhDanhDonViDTO) {
        log.debug("Request to save MaDinhDanhDonVi : {}", maDinhDanhDonViDTO);
        MaDinhDanhDonVi maDinhDanhDonVi = maDinhDanhDonViMapper.toEntity(maDinhDanhDonViDTO);
        maDinhDanhDonVi = maDinhDanhDonViRepository.save(maDinhDanhDonVi);
        return maDinhDanhDonViMapper.toDto(maDinhDanhDonVi);
    }

    /**
     * Get all the maDinhDanhDonVis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<MaDinhDanhDonViDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MaDinhDanhDonVis");
        return maDinhDanhDonViRepository.findAll(pageable)
            .map(maDinhDanhDonViMapper::toDto);
    }


    /**
     * Get one maDinhDanhDonVi by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<MaDinhDanhDonViDTO> findOne(Long id) {
        log.debug("Request to get MaDinhDanhDonVi : {}", id);
        return maDinhDanhDonViRepository.findById(id)
            .map(maDinhDanhDonViMapper::toDto);
    }

    /**
     * Delete the maDinhDanhDonVi by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MaDinhDanhDonVi : {}", id);
        maDinhDanhDonViRepository.deleteById(id);
    }
}
