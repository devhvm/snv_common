package com.manager.common.web.rest;

import com.manager.common.service.NoiDungDauVaoService;
import com.manager.common.service.dto.NoiDungDauVaoDTO;
import com.manager.common.web.rest.errors.BadRequestAlertException;
import com.manager.common.web.rest.util.HeaderUtil;
import com.manager.common.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NoiDungDauVao.
 */
@RestController
@RequestMapping("/api")
public class NoiDungDauVaoResource {

    private final Logger log = LoggerFactory.getLogger(NoiDungDauVaoResource.class);

    private static final String ENTITY_NAME = "commonNoiDungDauVao";

    private final NoiDungDauVaoService noiDungDauVaoService;

    public NoiDungDauVaoResource(NoiDungDauVaoService noiDungDauVaoService) {
        this.noiDungDauVaoService = noiDungDauVaoService;
    }

    /**
     * POST  /noi-dung-dau-vaos : Create a new noiDungDauVao.
     *
     * @param noiDungDauVaoDTO the noiDungDauVaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new noiDungDauVaoDTO, or with status 400 (Bad Request) if the noiDungDauVao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/noi-dung-dau-vaos")
    public ResponseEntity<NoiDungDauVaoDTO> createNoiDungDauVao(@Valid @RequestBody NoiDungDauVaoDTO noiDungDauVaoDTO) throws URISyntaxException {
        log.debug("REST request to save NoiDungDauVao : {}", noiDungDauVaoDTO);
        if (noiDungDauVaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new noiDungDauVao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoiDungDauVaoDTO result = noiDungDauVaoService.save(noiDungDauVaoDTO);
        return ResponseEntity.created(new URI("/api/noi-dung-dau-vaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /noi-dung-dau-vaos : Updates an existing noiDungDauVao.
     *
     * @param noiDungDauVaoDTO the noiDungDauVaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated noiDungDauVaoDTO,
     * or with status 400 (Bad Request) if the noiDungDauVaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the noiDungDauVaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/noi-dung-dau-vaos")
    public ResponseEntity<NoiDungDauVaoDTO> updateNoiDungDauVao(@Valid @RequestBody NoiDungDauVaoDTO noiDungDauVaoDTO) throws URISyntaxException {
        log.debug("REST request to update NoiDungDauVao : {}", noiDungDauVaoDTO);
        if (noiDungDauVaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NoiDungDauVaoDTO result = noiDungDauVaoService.save(noiDungDauVaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, noiDungDauVaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /noi-dung-dau-vaos : get all the noiDungDauVaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of noiDungDauVaos in body
     */
    @GetMapping("/noi-dung-dau-vaos")
    public ResponseEntity<List<NoiDungDauVaoDTO>> getAllNoiDungDauVaos(Pageable pageable) {
        log.debug("REST request to get a page of NoiDungDauVaos");
        Page<NoiDungDauVaoDTO> page = noiDungDauVaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/noi-dung-dau-vaos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /noi-dung-dau-vaos/:id : get the "id" noiDungDauVao.
     *
     * @param id the id of the noiDungDauVaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the noiDungDauVaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/noi-dung-dau-vaos/{id}")
    public ResponseEntity<NoiDungDauVaoDTO> getNoiDungDauVao(@PathVariable Long id) {
        log.debug("REST request to get NoiDungDauVao : {}", id);
        Optional<NoiDungDauVaoDTO> noiDungDauVaoDTO = noiDungDauVaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noiDungDauVaoDTO);
    }

    /**
     * DELETE  /noi-dung-dau-vaos/:id : delete the "id" noiDungDauVao.
     *
     * @param id the id of the noiDungDauVaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/noi-dung-dau-vaos/{id}")
    public ResponseEntity<Void> deleteNoiDungDauVao(@PathVariable Long id) {
        log.debug("REST request to delete NoiDungDauVao : {}", id);
        noiDungDauVaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
