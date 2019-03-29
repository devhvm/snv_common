package com.manager.common.web.rest;
import com.manager.common.service.DonViService;
import com.manager.common.web.rest.errors.BadRequestAlertException;
import com.manager.common.web.rest.util.HeaderUtil;
import com.manager.common.web.rest.util.PaginationUtil;
import com.manager.common.service.dto.DonViDTO;
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
 * REST controller for managing DonVi.
 */
@RestController
@RequestMapping("/api")
public class DonViResource {

    private final Logger log = LoggerFactory.getLogger(DonViResource.class);

    private static final String ENTITY_NAME = "commonDonVi";

    private final DonViService donViService;

    public DonViResource(DonViService donViService) {
        this.donViService = donViService;
    }

    /**
     * POST  /don-vis : Create a new donVi.
     *
     * @param donViDTO the donViDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new donViDTO, or with status 400 (Bad Request) if the donVi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/don-vis")
    public ResponseEntity<DonViDTO> createDonVi(@Valid @RequestBody DonViDTO donViDTO) throws URISyntaxException {
        log.debug("REST request to save DonVi : {}", donViDTO);
        if (donViDTO.getId() != null) {
            throw new BadRequestAlertException("A new donVi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DonViDTO result = donViService.save(donViDTO);
        return ResponseEntity.created(new URI("/api/don-vis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /don-vis : Updates an existing donVi.
     *
     * @param donViDTO the donViDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated donViDTO,
     * or with status 400 (Bad Request) if the donViDTO is not valid,
     * or with status 500 (Internal Server Error) if the donViDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/don-vis")
    public ResponseEntity<DonViDTO> updateDonVi(@Valid @RequestBody DonViDTO donViDTO) throws URISyntaxException {
        log.debug("REST request to update DonVi : {}", donViDTO);
        if (donViDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DonViDTO result = donViService.save(donViDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, donViDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /don-vis : get all the donVis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of donVis in body
     */
    @GetMapping("/don-vis")
    public ResponseEntity<List<DonViDTO>> getAllDonVis(Pageable pageable) {
        log.debug("REST request to get a page of DonVis");
        Page<DonViDTO> page = donViService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/don-vis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /don-vis/:id : get the "id" donVi.
     *
     * @param id the id of the donViDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the donViDTO, or with status 404 (Not Found)
     */
    @GetMapping("/don-vis/{id}")
    public ResponseEntity<DonViDTO> getDonVi(@PathVariable Long id) {
        log.debug("REST request to get DonVi : {}", id);
        Optional<DonViDTO> donViDTO = donViService.findOne(id);
        return ResponseUtil.wrapOrNotFound(donViDTO);
    }

    /**
     * DELETE  /don-vis/:id : delete the "id" donVi.
     *
     * @param id the id of the donViDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/don-vis/{id}")
    public ResponseEntity<Void> deleteDonVi(@PathVariable Long id) {
        log.debug("REST request to delete DonVi : {}", id);
        donViService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
