package com.manager.common.service;

import com.manager.common.service.dto.DoiTuongDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DoiTuong.
 */
public interface DoiTuongService {

    /**
     * Save a doiTuong.
     *
     * @param doiTuongDTO the entity to save
     * @return the persisted entity
     */
    DoiTuongDTO save(DoiTuongDTO doiTuongDTO);

    /**
     * Get all the doiTuongs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DoiTuongDTO> findAll(Pageable pageable);


    /**
     * Get the "id" doiTuong.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DoiTuongDTO> findOne(Long id);

    /**
     * Delete the "id" doiTuong.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
