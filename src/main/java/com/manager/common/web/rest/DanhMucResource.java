package com.manager.common.web.rest;

import com.manager.common.service.DanhMucService;
import com.manager.common.service.dto.DanhMucDTO;
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
 * REST controller for managing DanhMuc.
 */
@RestController
@RequestMapping("/api")
public class DanhMucResource {

    private final Logger log = LoggerFactory.getLogger(DanhMucResource.class);

    private static final String ENTITY_NAME = "commonDanhMuc";

    private final DanhMucService danhMucService;

    public DanhMucResource(DanhMucService danhMucService) {
        this.danhMucService = danhMucService;
    }

    /**
     * POST  /danh-mucs : Create a new danhMuc.
     *
     * @param danhMucDTO the danhMucDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new danhMucDTO, or with status 400 (Bad Request) if the danhMuc has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/danh-mucs")
    public ResponseEntity<DanhMucDTO> createDanhMuc(@Valid @RequestBody DanhMucDTO danhMucDTO) throws URISyntaxException {
        log.debug("REST request to save DanhMuc : {}", danhMucDTO);
        if (danhMucDTO.getId() != null) {
            throw new BadRequestAlertException("A new danhMuc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DanhMucDTO result = danhMucService.save(danhMucDTO);
        return ResponseEntity.created(new URI("/api/danh-mucs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /danh-mucs : Updates an existing danhMuc.
     *
     * @param danhMucDTO the danhMucDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated danhMucDTO,
     * or with status 400 (Bad Request) if the danhMucDTO is not valid,
     * or with status 500 (Internal Server Error) if the danhMucDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/danh-mucs")
    public ResponseEntity<DanhMucDTO> updateDanhMuc(@Valid @RequestBody DanhMucDTO danhMucDTO) throws URISyntaxException {
        log.debug("REST request to update DanhMuc : {}", danhMucDTO);
        if (danhMucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DanhMucDTO result = danhMucService.save(danhMucDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, danhMucDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /danh-mucs : get all the danhMucs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of danhMucs in body
     */
    @GetMapping("/danh-mucs")
    public ResponseEntity<List<DanhMucDTO>> getAllDanhMucs(Pageable pageable) {
        log.debug("REST request to get a page of DanhMucs");
        Page<DanhMucDTO> page = danhMucService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/danh-mucs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /danh-mucs/:id : get the "id" danhMuc.
     *
     * @param id the id of the danhMucDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the danhMucDTO, or with status 404 (Not Found)
     */
    @GetMapping("/danh-mucs/{id}")
    public ResponseEntity<DanhMucDTO> getDanhMuc(@PathVariable Long id) {
        log.debug("REST request to get DanhMuc : {}", id);
        Optional<DanhMucDTO> danhMucDTO = danhMucService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danhMucDTO);
    }

    /**
     * DELETE  /danh-mucs/:id : delete the "id" danhMuc.
     *
     * @param id the id of the danhMucDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/danh-mucs/{id}")
    public ResponseEntity<Void> deleteDanhMuc(@PathVariable Long id) {
        log.debug("REST request to delete DanhMuc : {}", id);
        danhMucService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
