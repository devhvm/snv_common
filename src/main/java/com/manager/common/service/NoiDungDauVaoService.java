package com.manager.common.service;

import com.manager.common.domain.NoiDungDauVao;
import com.manager.common.repository.NoiDungDauVaoRepository;
import com.manager.common.service.dto.NoiDungDauVaoDTO;
import com.manager.common.service.mapper.NoiDungDauVaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NoiDungDauVao.
 */
@Service
@Transactional
public class NoiDungDauVaoService {

    private final Logger log = LoggerFactory.getLogger(NoiDungDauVaoService.class);

    private final NoiDungDauVaoRepository noiDungDauVaoRepository;

    private final NoiDungDauVaoMapper noiDungDauVaoMapper;

    public NoiDungDauVaoService(NoiDungDauVaoRepository noiDungDauVaoRepository, NoiDungDauVaoMapper noiDungDauVaoMapper) {
        this.noiDungDauVaoRepository = noiDungDauVaoRepository;
        this.noiDungDauVaoMapper = noiDungDauVaoMapper;
    }

    /**
     * Save a noiDungDauVao.
     *
     * @param noiDungDauVaoDTO the entity to save
     * @return the persisted entity
     */
    public NoiDungDauVaoDTO save(NoiDungDauVaoDTO noiDungDauVaoDTO) {
        log.debug("Request to save NoiDungDauVao : {}", noiDungDauVaoDTO);
        NoiDungDauVao noiDungDauVao = noiDungDauVaoMapper.toEntity(noiDungDauVaoDTO);
        noiDungDauVao = noiDungDauVaoRepository.save(noiDungDauVao);
        return noiDungDauVaoMapper.toDto(noiDungDauVao);
    }

    /**
     * Get all the noiDungDauVaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<NoiDungDauVaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoiDungDauVaos");
        return noiDungDauVaoRepository.findAll(pageable)
            .map(noiDungDauVaoMapper::toDto);
    }


    /**
     * Get one noiDungDauVao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<NoiDungDauVaoDTO> findOne(Long id) {
        log.debug("Request to get NoiDungDauVao : {}", id);
        return noiDungDauVaoRepository.findById(id)
            .map(noiDungDauVaoMapper::toDto);
    }

    /**
     * Delete the noiDungDauVao by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete NoiDungDauVao : {}", id);
        noiDungDauVaoRepository.deleteById(id);
    }
}
