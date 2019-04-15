package com.manager.common.web.rest;
import com.manager.common.service.ChiTieuService;
import com.manager.common.web.rest.errors.BadRequestAlertException;
import com.manager.common.web.rest.util.HeaderUtil;
import com.manager.common.web.rest.util.PaginationUtil;
import com.manager.common.service.dto.ChiTieuDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ChiTieu.
 */
@RestController
@RequestMapping("/api")
public class ChiTieuResource {

    private final Logger log = LoggerFactory.getLogger(ChiTieuResource.class);

    private static final String ENTITY_NAME = "commonChiTieu";

    private final ChiTieuService chiTieuService;

    public ChiTieuResource(ChiTieuService chiTieuService) {
        this.chiTieuService = chiTieuService;
    }

    /**
     * POST  /chi-tieus : Create a new chiTieu.
     *
     * @param chiTieuDTO the chiTieuDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chiTieuDTO, or with status 400 (Bad Request) if the chiTieu has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/chi-tieus")
    public ResponseEntity<ChiTieuDTO> createChiTieu(@Valid @RequestBody ChiTieuDTO chiTieuDTO) throws URISyntaxException {
        log.debug("REST request to save ChiTieu : {}", chiTieuDTO);
        if (chiTieuDTO.getId() != null) {
            throw new BadRequestAlertException("A new chiTieu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChiTieuDTO result = chiTieuService.save(chiTieuDTO);
        return ResponseEntity.created(new URI("/api/chi-tieus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chi-tieus : Updates an existing chiTieu.
     *
     * @param chiTieuDTO the chiTieuDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chiTieuDTO,
     * or with status 400 (Bad Request) if the chiTieuDTO is not valid,
     * or with status 500 (Internal Server Error) if the chiTieuDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/chi-tieus")
    public ResponseEntity<ChiTieuDTO> updateChiTieu(@Valid @RequestBody ChiTieuDTO chiTieuDTO) throws URISyntaxException {
        log.debug("REST request to update ChiTieu : {}", chiTieuDTO);
        if (chiTieuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChiTieuDTO result = chiTieuService.save(chiTieuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chiTieuDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chi-tieus : get all the chiTieus.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of chiTieus in body
     */
    @GetMapping("/chi-tieus")
    public ResponseEntity<List<ChiTieuDTO>> getAllChiTieus(Pageable pageable) {
        log.debug("REST request to get a page of ChiTieus");
        Page<ChiTieuDTO> page = chiTieuService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/chi-tieus");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /chi-tieus/:id : get the "id" chiTieu.
     *
     * @param id the id of the chiTieuDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chiTieuDTO, or with status 404 (Not Found)
     */
    @GetMapping("/chi-tieus/{id}")
    public ResponseEntity<ChiTieuDTO> getChiTieu(@PathVariable Long id) {
        log.debug("REST request to get ChiTieu : {}", id);
        Optional<ChiTieuDTO> chiTieuDTO = chiTieuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chiTieuDTO);
    }
    
    /**
     * GET  /chi-tieus/:id : get the "id" chiTieu.
     *
     * @param id the id of the chiTieuDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chiTieuDTO, or with status 404 (Not Found)
     */
    @GetMapping("/chi-tieus/{code}")
    public ResponseEntity<ChiTieuDTO> getChiTieu(@PathVariable String code) {
        log.debug("REST request to get ChiTieu : {}", code);
        Optional<ChiTieuDTO> chiTieuDTO = null;
        return ResponseUtil.wrapOrNotFound(chiTieuDTO);
    }

    /**
     * DELETE  /chi-tieus/:id : delete the "id" chiTieu.
     *
     * @param id the id of the chiTieuDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/chi-tieus/{id}")
    public ResponseEntity<Void> deleteChiTieu(@PathVariable Long id) {
        log.debug("REST request to delete ChiTieu : {}", id);
        chiTieuService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
