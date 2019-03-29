package com.manager.common.service;

import com.manager.common.service.dto.NoiDungDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NoiDung.
 */
public interface NoiDungService {

    /**
     * Save a noiDung.
     *
     * @param noiDungDTO the entity to save
     * @return the persisted entity
     */
    NoiDungDTO save(NoiDungDTO noiDungDTO);

    /**
     * Get all the noiDungs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NoiDungDTO> findAll(Pageable pageable);


    /**
     * Get the "id" noiDung.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NoiDungDTO> findOne(Long id);

    /**
     * Delete the "id" noiDung.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
