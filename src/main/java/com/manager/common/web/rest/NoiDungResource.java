package com.manager.common.web.rest;

import com.manager.common.service.NoiDungService;
import com.manager.common.service.dto.NoiDungDTO;
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
 * REST controller for managing NoiDung.
 */
@RestController
@RequestMapping("/api")
public class NoiDungResource {

    private final Logger log = LoggerFactory.getLogger(NoiDungResource.class);

    private static final String ENTITY_NAME = "commonNoiDung";

    private final NoiDungService noiDungService;

    public NoiDungResource(NoiDungService noiDungService) {
        this.noiDungService = noiDungService;
    }

    /**
     * POST  /noi-dungs : Create a new noiDung.
     *
     * @param noiDungDTO the noiDungDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new noiDungDTO, or with status 400 (Bad Request) if the noiDung has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/noi-dungs")
    public ResponseEntity<NoiDungDTO> createNoiDung(@Valid @RequestBody NoiDungDTO noiDungDTO) throws URISyntaxException {
        log.debug("REST request to save NoiDung : {}", noiDungDTO);
        if (noiDungDTO.getId() != null) {
            throw new BadRequestAlertException("A new noiDung cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoiDungDTO result = noiDungService.save(noiDungDTO);
        return ResponseEntity.created(new URI("/api/noi-dungs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /noi-dungs : Updates an existing noiDung.
     *
     * @param noiDungDTO the noiDungDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated noiDungDTO,
     * or with status 400 (Bad Request) if the noiDungDTO is not valid,
     * or with status 500 (Internal Server Error) if the noiDungDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/noi-dungs")
    public ResponseEntity<NoiDungDTO> updateNoiDung(@Valid @RequestBody NoiDungDTO noiDungDTO) throws URISyntaxException {
        log.debug("REST request to update NoiDung : {}", noiDungDTO);
        if (noiDungDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NoiDungDTO result = noiDungService.save(noiDungDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, noiDungDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /noi-dungs : get all the noiDungs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of noiDungs in body
     */
    @GetMapping("/noi-dungs")
    public ResponseEntity<List<NoiDungDTO>> getAllNoiDungs(Pageable pageable) {
        log.debug("REST request to get a page of NoiDungs");
        Page<NoiDungDTO> page = noiDungService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/noi-dungs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /noi-dungs/:id : get the "id" noiDung.
     *
     * @param id the id of the noiDungDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the noiDungDTO, or with status 404 (Not Found)
     */
    @GetMapping("/noi-dungs/{id}")
    public ResponseEntity<NoiDungDTO> getNoiDung(@PathVariable Long id) {
        log.debug("REST request to get NoiDung : {}", id);
        Optional<NoiDungDTO> noiDungDTO = noiDungService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noiDungDTO);
    }

    /**
     * DELETE  /noi-dungs/:id : delete the "id" noiDung.
     *
     * @param id the id of the noiDungDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/noi-dungs/{id}")
    public ResponseEntity<Void> deleteNoiDung(@PathVariable Long id) {
        log.debug("REST request to delete NoiDung : {}", id);
        noiDungService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
