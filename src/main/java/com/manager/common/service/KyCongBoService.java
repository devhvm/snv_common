package com.manager.common.service;

import com.manager.common.domain.KyCongBo;
import com.manager.common.repository.KyCongBoRepository;
import com.manager.common.service.dto.KyCongBoDTO;
import com.manager.common.service.mapper.KyCongBoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing KyCongBo.
 */
@Service
@Transactional
public class KyCongBoService {

    private final Logger log = LoggerFactory.getLogger(KyCongBoService.class);

    private final KyCongBoRepository kyCongBoRepository;

    private final KyCongBoMapper kyCongBoMapper;

    public KyCongBoService(KyCongBoRepository kyCongBoRepository, KyCongBoMapper kyCongBoMapper) {
        this.kyCongBoRepository = kyCongBoRepository;
        this.kyCongBoMapper = kyCongBoMapper;
    }

    /**
     * Save a kyCongBo.
     *
     * @param kyCongBoDTO the entity to save
     * @return the persisted entity
     */
    public KyCongBoDTO save(KyCongBoDTO kyCongBoDTO) {
        log.debug("Request to save KyCongBo : {}", kyCongBoDTO);
        KyCongBo kyCongBo = kyCongBoMapper.toEntity(kyCongBoDTO);
        kyCongBo = kyCongBoRepository.save(kyCongBo);
        return kyCongBoMapper.toDto(kyCongBo);
    }

    /**
     * Get all the kyCongBos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<KyCongBoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all KyCongBos");
        return kyCongBoRepository.findAll(pageable)
            .map(kyCongBoMapper::toDto);
    }


    /**
     * Get one kyCongBo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<KyCongBoDTO> findOne(Long id) {
        log.debug("Request to get KyCongBo : {}", id);
        return kyCongBoRepository.findById(id)
            .map(kyCongBoMapper::toDto);
    }

    /**
     * Delete the kyCongBo by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete KyCongBo : {}", id);
        kyCongBoRepository.deleteById(id);
    }
}
