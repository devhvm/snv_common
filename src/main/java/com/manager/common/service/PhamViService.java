package com.manager.common.service;

import com.manager.common.service.dto.PhamViDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PhamVi.
 */
public interface PhamViService {

    /**
     * Save a phamVi.
     *
     * @param phamViDTO the entity to save
     * @return the persisted entity
     */
    PhamViDTO save(PhamViDTO phamViDTO);

    /**
     * Get all the phamVis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PhamViDTO> findAll(Pageable pageable);
    /**
     * Get all the PhamViDTO where Donvi is null.
     *
     * @return the list of entities
     */
    List<PhamViDTO> findAllWhereDonviIsNull();


    /**
     * Get the "id" phamVi.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PhamViDTO> findOne(Long id);

    /**
     * Delete the "id" phamVi.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
