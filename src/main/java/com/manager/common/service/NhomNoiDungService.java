package com.manager.common.service;

import com.manager.common.service.dto.NhomNoiDungDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NhomNoiDung.
 */
public interface NhomNoiDungService {

    /**
     * Save a nhomNoiDung.
     *
     * @param nhomNoiDungDTO the entity to save
     * @return the persisted entity
     */
    NhomNoiDungDTO save(NhomNoiDungDTO nhomNoiDungDTO);

    /**
     * Get all the nhomNoiDungs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NhomNoiDungDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nhomNoiDung.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NhomNoiDungDTO> findOne(Long id);

    /**
     * Delete the "id" nhomNoiDung.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
