package com.manager.common.web.rest;

import com.manager.common.service.CoQuanChuTriService;
import com.manager.common.service.dto.CoQuanChuTriDTO;
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
 * REST controller for managing CoQuanChuTri.
 */
@RestController
@RequestMapping("/api")
public class CoQuanChuTriResource {

    private final Logger log = LoggerFactory.getLogger(CoQuanChuTriResource.class);

    private static final String ENTITY_NAME = "commonCoQuanChuTri";

    private final CoQuanChuTriService coQuanChuTriService;

    public CoQuanChuTriResource(CoQuanChuTriService coQuanChuTriService) {
        this.coQuanChuTriService = coQuanChuTriService;
    }

    /**
     * POST  /co-quan-chu-tris : Create a new coQuanChuTri.
     *
     * @param coQuanChuTriDTO the coQuanChuTriDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new coQuanChuTriDTO, or with status 400 (Bad Request) if the coQuanChuTri has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/co-quan-chu-tris")
    public ResponseEntity<CoQuanChuTriDTO> createCoQuanChuTri(@Valid @RequestBody CoQuanChuTriDTO coQuanChuTriDTO) throws URISyntaxException {
        log.debug("REST request to save CoQuanChuTri : {}", coQuanChuTriDTO);
        if (coQuanChuTriDTO.getId() != null) {
            throw new BadRequestAlertException("A new coQuanChuTri cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoQuanChuTriDTO result = coQuanChuTriService.save(coQuanChuTriDTO);
        return ResponseEntity.created(new URI("/api/co-quan-chu-tris/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /co-quan-chu-tris : Updates an existing coQuanChuTri.
     *
     * @param coQuanChuTriDTO the coQuanChuTriDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated coQuanChuTriDTO,
     * or with status 400 (Bad Request) if the coQuanChuTriDTO is not valid,
     * or with status 500 (Internal Server Error) if the coQuanChuTriDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/co-quan-chu-tris")
    public ResponseEntity<CoQuanChuTriDTO> updateCoQuanChuTri(@Valid @RequestBody CoQuanChuTriDTO coQuanChuTriDTO) throws URISyntaxException {
        log.debug("REST request to update CoQuanChuTri : {}", coQuanChuTriDTO);
        if (coQuanChuTriDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CoQuanChuTriDTO result = coQuanChuTriService.save(coQuanChuTriDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, coQuanChuTriDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /co-quan-chu-tris : get all the coQuanChuTris.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of coQuanChuTris in body
     */
    @GetMapping("/co-quan-chu-tris")
    public ResponseEntity<List<CoQuanChuTriDTO>> getAllCoQuanChuTris(Pageable pageable) {
        log.debug("REST request to get a page of CoQuanChuTris");
        Page<CoQuanChuTriDTO> page = coQuanChuTriService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/co-quan-chu-tris");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /co-quan-chu-tris/:id : get the "id" coQuanChuTri.
     *
     * @param id the id of the coQuanChuTriDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the coQuanChuTriDTO, or with status 404 (Not Found)
     */
    @GetMapping("/co-quan-chu-tris/{id}")
    public ResponseEntity<CoQuanChuTriDTO> getCoQuanChuTri(@PathVariable Long id) {
        log.debug("REST request to get CoQuanChuTri : {}", id);
        Optional<CoQuanChuTriDTO> coQuanChuTriDTO = coQuanChuTriService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coQuanChuTriDTO);
    }

    /**
     * DELETE  /co-quan-chu-tris/:id : delete the "id" coQuanChuTri.
     *
     * @param id the id of the coQuanChuTriDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/co-quan-chu-tris/{id}")
    public ResponseEntity<Void> deleteCoQuanChuTri(@PathVariable Long id) {
        log.debug("REST request to delete CoQuanChuTri : {}", id);
        coQuanChuTriService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
