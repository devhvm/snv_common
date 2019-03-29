package com.manager.common.service;

import com.manager.common.service.dto.DonViDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DonVi.
 */
public interface DonViService {

    /**
     * Save a donVi.
     *
     * @param donViDTO the entity to save
     * @return the persisted entity
     */
    DonViDTO save(DonViDTO donViDTO);

    /**
     * Get all the donVis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DonViDTO> findAll(Pageable pageable);


    /**
     * Get the "id" donVi.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DonViDTO> findOne(Long id);

    /**
     * Delete the "id" donVi.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
