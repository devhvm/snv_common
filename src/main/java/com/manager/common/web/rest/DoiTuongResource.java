package com.manager.common.web.rest;

import com.manager.common.service.DoiTuongService;
import com.manager.common.service.dto.DoiTuongDTO;
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
 * REST controller for managing DoiTuong.
 */
@RestController
@RequestMapping("/api")
public class DoiTuongResource {

    private final Logger log = LoggerFactory.getLogger(DoiTuongResource.class);

    private static final String ENTITY_NAME = "commonDoiTuong";

    private final DoiTuongService doiTuongService;

    public DoiTuongResource(DoiTuongService doiTuongService) {
        this.doiTuongService = doiTuongService;
    }

    /**
     * POST  /doi-tuongs : Create a new doiTuong.
     *
     * @param doiTuongDTO the doiTuongDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new doiTuongDTO, or with status 400 (Bad Request) if the doiTuong has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/doi-tuongs")
    public ResponseEntity<DoiTuongDTO> createDoiTuong(@Valid @RequestBody DoiTuongDTO doiTuongDTO) throws URISyntaxException {
        log.debug("REST request to save DoiTuong : {}", doiTuongDTO);
        if (doiTuongDTO.getId() != null) {
            throw new BadRequestAlertException("A new doiTuong cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DoiTuongDTO result = doiTuongService.save(doiTuongDTO);
        return ResponseEntity.created(new URI("/api/doi-tuongs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /doi-tuongs : Updates an existing doiTuong.
     *
     * @param doiTuongDTO the doiTuongDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated doiTuongDTO,
     * or with status 400 (Bad Request) if the doiTuongDTO is not valid,
     * or with status 500 (Internal Server Error) if the doiTuongDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/doi-tuongs")
    public ResponseEntity<DoiTuongDTO> updateDoiTuong(@Valid @RequestBody DoiTuongDTO doiTuongDTO) throws URISyntaxException {
        log.debug("REST request to update DoiTuong : {}", doiTuongDTO);
        if (doiTuongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DoiTuongDTO result = doiTuongService.save(doiTuongDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, doiTuongDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /doi-tuongs : get all the doiTuongs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of doiTuongs in body
     */
    @GetMapping("/doi-tuongs")
    public ResponseEntity<List<DoiTuongDTO>> getAllDoiTuongs(Pageable pageable) {
        log.debug("REST request to get a page of DoiTuongs");
        Page<DoiTuongDTO> page = doiTuongService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/doi-tuongs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /doi-tuongs/:id : get the "id" doiTuong.
     *
     * @param id the id of the doiTuongDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the doiTuongDTO, or with status 404 (Not Found)
     */
    @GetMapping("/doi-tuongs/{id}")
    public ResponseEntity<DoiTuongDTO> getDoiTuong(@PathVariable Long id) {
        log.debug("REST request to get DoiTuong : {}", id);
        Optional<DoiTuongDTO> doiTuongDTO = doiTuongService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doiTuongDTO);
    }

    /**
     * DELETE  /doi-tuongs/:id : delete the "id" doiTuong.
     *
     * @param id the id of the doiTuongDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/doi-tuongs/{id}")
    public ResponseEntity<Void> deleteDoiTuong(@PathVariable Long id) {
        log.debug("REST request to delete DoiTuong : {}", id);
        doiTuongService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
