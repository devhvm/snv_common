package com.manager.common.service;

import com.manager.common.service.dto.NhomDanhMucDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NhomDanhMuc.
 */
public interface NhomDanhMucService {

    /**
     * Save a nhomDanhMuc.
     *
     * @param nhomDanhMucDTO the entity to save
     * @return the persisted entity
     */
    NhomDanhMucDTO save(NhomDanhMucDTO nhomDanhMucDTO);

    /**
     * Get all the nhomDanhMucs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NhomDanhMucDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nhomDanhMuc.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NhomDanhMucDTO> findOne(Long id);

    /**
     * Delete the "id" nhomDanhMuc.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
