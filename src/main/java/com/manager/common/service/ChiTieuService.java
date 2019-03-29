package com.manager.common.service;

import com.manager.common.service.dto.ChiTieuDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ChiTieu.
 */
public interface ChiTieuService {

    /**
     * Save a chiTieu.
     *
     * @param chiTieuDTO the entity to save
     * @return the persisted entity
     */
    ChiTieuDTO save(ChiTieuDTO chiTieuDTO);

    /**
     * Get all the chiTieus.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ChiTieuDTO> findAll(Pageable pageable);


    /**
     * Get the "id" chiTieu.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ChiTieuDTO> findOne(Long id);

    /**
     * Delete the "id" chiTieu.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
