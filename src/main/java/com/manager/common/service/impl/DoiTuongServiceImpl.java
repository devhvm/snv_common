package com.manager.common.service.impl;

import com.manager.common.service.DoiTuongService;
import com.manager.common.domain.DoiTuong;
import com.manager.common.repository.DoiTuongRepository;
import com.manager.common.service.dto.DoiTuongDTO;
import com.manager.common.service.mapper.DoiTuongMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DoiTuong.
 */
@Service
@Transactional
public class DoiTuongServiceImpl implements DoiTuongService {

    private final Logger log = LoggerFactory.getLogger(DoiTuongServiceImpl.class);

    private final DoiTuongRepository doiTuongRepository;

    private final DoiTuongMapper doiTuongMapper;

    public DoiTuongServiceImpl(DoiTuongRepository doiTuongRepository, DoiTuongMapper doiTuongMapper) {
        this.doiTuongRepository = doiTuongRepository;
        this.doiTuongMapper = doiTuongMapper;
    }

    /**
     * Save a doiTuong.
     *
     * @param doiTuongDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DoiTuongDTO save(DoiTuongDTO doiTuongDTO) {
        log.debug("Request to save DoiTuong : {}", doiTuongDTO);
        DoiTuong doiTuong = doiTuongMapper.toEntity(doiTuongDTO);
        doiTuong = doiTuongRepository.save(doiTuong);
        return doiTuongMapper.toDto(doiTuong);
    }

    /**
     * Get all the doiTuongs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DoiTuongDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DoiTuongs");
        return doiTuongRepository.findAll(pageable)
            .map(doiTuongMapper::toDto);
    }


    /**
     * Get one doiTuong by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DoiTuongDTO> findOne(Long id) {
        log.debug("Request to get DoiTuong : {}", id);
        return doiTuongRepository.findById(id)
            .map(doiTuongMapper::toDto);
    }

    /**
     * Delete the doiTuong by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DoiTuong : {}", id);        doiTuongRepository.deleteById(id);
    }
}
