package com.manager.common.web.rest;
import com.manager.common.service.DonViTinhService;
import com.manager.common.web.rest.errors.BadRequestAlertException;
import com.manager.common.web.rest.util.HeaderUtil;
import com.manager.common.web.rest.util.PaginationUtil;
import com.manager.common.service.dto.DonViTinhDTO;
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
 * REST controller for managing DonViTinh.
 */
@RestController
@RequestMapping("/api")
public class DonViTinhResource {

    private final Logger log = LoggerFactory.getLogger(DonViTinhResource.class);

    private static final String ENTITY_NAME = "commonDonViTinh";

    private final DonViTinhService donViTinhService;

    public DonViTinhResource(DonViTinhService donViTinhService) {
        this.donViTinhService = donViTinhService;
    }

    /**
     * POST  /don-vi-tinhs : Create a new donViTinh.
     *
     * @param donViTinhDTO the donViTinhDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new donViTinhDTO, or with status 400 (Bad Request) if the donViTinh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/don-vi-tinhs")
    public ResponseEntity<DonViTinhDTO> createDonViTinh(@Valid @RequestBody DonViTinhDTO donViTinhDTO) throws URISyntaxException {
        log.debug("REST request to save DonViTinh : {}", donViTinhDTO);
        if (donViTinhDTO.getId() != null) {
            throw new BadRequestAlertException("A new donViTinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DonViTinhDTO result = donViTinhService.save(donViTinhDTO);
        return ResponseEntity.created(new URI("/api/don-vi-tinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /don-vi-tinhs : Updates an existing donViTinh.
     *
     * @param donViTinhDTO the donViTinhDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated donViTinhDTO,
     * or with status 400 (Bad Request) if the donViTinhDTO is not valid,
     * or with status 500 (Internal Server Error) if the donViTinhDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/don-vi-tinhs")
    public ResponseEntity<DonViTinhDTO> updateDonViTinh(@Valid @RequestBody DonViTinhDTO donViTinhDTO) throws URISyntaxException {
        log.debug("REST request to update DonViTinh : {}", donViTinhDTO);
        if (donViTinhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DonViTinhDTO result = donViTinhService.save(donViTinhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, donViTinhDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /don-vi-tinhs : get all the donViTinhs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of donViTinhs in body
     */
    @GetMapping("/don-vi-tinhs")
    public ResponseEntity<List<DonViTinhDTO>> getAllDonViTinhs(Pageable pageable) {
        log.debug("REST request to get a page of DonViTinhs");
        Page<DonViTinhDTO> page = donViTinhService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/don-vi-tinhs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /don-vi-tinhs/:id : get the "id" donViTinh.
     *
     * @param id the id of the donViTinhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the donViTinhDTO, or with status 404 (Not Found)
     */
    @GetMapping("/don-vi-tinhs/{id}")
    public ResponseEntity<DonViTinhDTO> getDonViTinh(@PathVariable Long id) {
        log.debug("REST request to get DonViTinh : {}", id);
        Optional<DonViTinhDTO> donViTinhDTO = donViTinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(donViTinhDTO);
    }

    /**
     * DELETE  /don-vi-tinhs/:id : delete the "id" donViTinh.
     *
     * @param id the id of the donViTinhDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/don-vi-tinhs/{id}")
    public ResponseEntity<Void> deleteDonViTinh(@PathVariable Long id) {
        log.debug("REST request to delete DonViTinh : {}", id);
        donViTinhService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
