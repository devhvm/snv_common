package com.manager.common.service;

import com.manager.common.service.dto.NhomChiTieuDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NhomChiTieu.
 */
public interface NhomChiTieuService {

    /**
     * Save a nhomChiTieu.
     *
     * @param nhomChiTieuDTO the entity to save
     * @return the persisted entity
     */
    NhomChiTieuDTO save(NhomChiTieuDTO nhomChiTieuDTO);

    /**
     * Get all the nhomChiTieus.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NhomChiTieuDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nhomChiTieu.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NhomChiTieuDTO> findOne(Long id);

    /**
     * Delete the "id" nhomChiTieu.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
