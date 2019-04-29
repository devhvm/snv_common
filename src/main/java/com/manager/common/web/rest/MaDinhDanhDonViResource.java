package com.manager.common.web.rest;

import com.manager.common.service.MaDinhDanhDonViService;
import com.manager.common.service.dto.MaDinhDanhDonViDTO;
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
 * REST controller for managing MaDinhDanhDonVi.
 */
@RestController
@RequestMapping("/api")
public class MaDinhDanhDonViResource {

    private final Logger log = LoggerFactory.getLogger(MaDinhDanhDonViResource.class);

    private static final String ENTITY_NAME = "commonMaDinhDanhDonVi";

    private final MaDinhDanhDonViService maDinhDanhDonViService;

    public MaDinhDanhDonViResource(MaDinhDanhDonViService maDinhDanhDonViService) {
        this.maDinhDanhDonViService = maDinhDanhDonViService;
    }

    /**
     * POST  /ma-dinh-danh-don-vis : Create a new maDinhDanhDonVi.
     *
     * @param maDinhDanhDonViDTO the maDinhDanhDonViDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new maDinhDanhDonViDTO, or with status 400 (Bad Request) if the maDinhDanhDonVi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ma-dinh-danh-don-vis")
    public ResponseEntity<MaDinhDanhDonViDTO> createMaDinhDanhDonVi(@Valid @RequestBody MaDinhDanhDonViDTO maDinhDanhDonViDTO) throws URISyntaxException {
        log.debug("REST request to save MaDinhDanhDonVi : {}", maDinhDanhDonViDTO);
        if (maDinhDanhDonViDTO.getId() != null) {
            throw new BadRequestAlertException("A new maDinhDanhDonVi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MaDinhDanhDonViDTO result = maDinhDanhDonViService.save(maDinhDanhDonViDTO);
        return ResponseEntity.created(new URI("/api/ma-dinh-danh-don-vis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ma-dinh-danh-don-vis : Updates an existing maDinhDanhDonVi.
     *
     * @param maDinhDanhDonViDTO the maDinhDanhDonViDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated maDinhDanhDonViDTO,
     * or with status 400 (Bad Request) if the maDinhDanhDonViDTO is not valid,
     * or with status 500 (Internal Server Error) if the maDinhDanhDonViDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ma-dinh-danh-don-vis")
    public ResponseEntity<MaDinhDanhDonViDTO> updateMaDinhDanhDonVi(@Valid @RequestBody MaDinhDanhDonViDTO maDinhDanhDonViDTO) throws URISyntaxException {
        log.debug("REST request to update MaDinhDanhDonVi : {}", maDinhDanhDonViDTO);
        if (maDinhDanhDonViDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MaDinhDanhDonViDTO result = maDinhDanhDonViService.save(maDinhDanhDonViDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, maDinhDanhDonViDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ma-dinh-danh-don-vis : get all the maDinhDanhDonVis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of maDinhDanhDonVis in body
     */
    @GetMapping("/ma-dinh-danh-don-vis")
    public ResponseEntity<List<MaDinhDanhDonViDTO>> getAllMaDinhDanhDonVis(Pageable pageable) {
        log.debug("REST request to get a page of MaDinhDanhDonVis");
        Page<MaDinhDanhDonViDTO> page = maDinhDanhDonViService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ma-dinh-danh-don-vis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ma-dinh-danh-don-vis/:id : get the "id" maDinhDanhDonVi.
     *
     * @param id the id of the maDinhDanhDonViDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the maDinhDanhDonViDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ma-dinh-danh-don-vis/{id}")
    public ResponseEntity<MaDinhDanhDonViDTO> getMaDinhDanhDonVi(@PathVariable Long id) {
        log.debug("REST request to get MaDinhDanhDonVi : {}", id);
        Optional<MaDinhDanhDonViDTO> maDinhDanhDonViDTO = maDinhDanhDonViService.findOne(id);
        return ResponseUtil.wrapOrNotFound(maDinhDanhDonViDTO);
    }

    /**
     * DELETE  /ma-dinh-danh-don-vis/:id : delete the "id" maDinhDanhDonVi.
     *
     * @param id the id of the maDinhDanhDonViDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ma-dinh-danh-don-vis/{id}")
    public ResponseEntity<Void> deleteMaDinhDanhDonVi(@PathVariable Long id) {
        log.debug("REST request to delete MaDinhDanhDonVi : {}", id);
        maDinhDanhDonViService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
