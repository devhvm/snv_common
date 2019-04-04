package com.manager.common.service;

import com.manager.common.domain.DonViTinh;
import com.manager.common.repository.DonViTinhRepository;
import com.manager.common.service.dto.DonViTinhDTO;
import com.manager.common.service.mapper.DonViTinhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DonViTinh.
 */
@Service
@Transactional
public class DonViTinhService {

    private final Logger log = LoggerFactory.getLogger(DonViTinhService.class);

    private final DonViTinhRepository donViTinhRepository;

    private final DonViTinhMapper donViTinhMapper;

    public DonViTinhService(DonViTinhRepository donViTinhRepository, DonViTinhMapper donViTinhMapper) {
        this.donViTinhRepository = donViTinhRepository;
        this.donViTinhMapper = donViTinhMapper;
    }

    /**
     * Save a donViTinh.
     *
     * @param donViTinhDTO the entity to save
     * @return the persisted entity
     */
    public DonViTinhDTO save(DonViTinhDTO donViTinhDTO) {
        log.debug("Request to save DonViTinh : {}", donViTinhDTO);
        DonViTinh donViTinh = donViTinhMapper.toEntity(donViTinhDTO);
        donViTinh = donViTinhRepository.save(donViTinh);
        return donViTinhMapper.toDto(donViTinh);
    }

    /**
     * Get all the donViTinhs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<DonViTinhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DonViTinhs");
        return donViTinhRepository.findAll(pageable)
            .map(donViTinhMapper::toDto);
    }


    /**
     * Get one donViTinh by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DonViTinhDTO> findOne(Long id) {
        log.debug("Request to get DonViTinh : {}", id);
        return donViTinhRepository.findById(id)
            .map(donViTinhMapper::toDto);
    }

    /**
     * Delete the donViTinh by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete DonViTinh : {}", id);
        donViTinhRepository.deleteById(id);
    }
}
