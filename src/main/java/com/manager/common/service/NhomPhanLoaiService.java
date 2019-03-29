package com.manager.common.service;

import com.manager.common.service.dto.NhomPhanLoaiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NhomPhanLoai.
 */
public interface NhomPhanLoaiService {

    /**
     * Save a nhomPhanLoai.
     *
     * @param nhomPhanLoaiDTO the entity to save
     * @return the persisted entity
     */
    NhomPhanLoaiDTO save(NhomPhanLoaiDTO nhomPhanLoaiDTO);

    /**
     * Get all the nhomPhanLoais.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NhomPhanLoaiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nhomPhanLoai.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NhomPhanLoaiDTO> findOne(Long id);

    /**
     * Delete the "id" nhomPhanLoai.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
