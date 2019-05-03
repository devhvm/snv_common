package com.manager.common.web.rest;
import com.manager.common.service.TieuChiService;
import com.manager.common.service.dto.coquanchutri.TieuChiDetailDTO;
import com.manager.common.web.rest.errors.BadRequestAlertException;
import com.manager.common.web.rest.util.HeaderUtil;
import com.manager.common.web.rest.util.PaginationUtil;
import com.manager.common.service.dto.TieuChiDTO;
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
 * REST controller for managing TieuChi.
 */
@RestController
@RequestMapping("/api")
public class TieuChiResource {

    private final Logger log = LoggerFactory.getLogger(TieuChiResource.class);

    private static final String ENTITY_NAME = "commonTieuChi";

    private final TieuChiService tieuChiService;

    public TieuChiResource(TieuChiService tieuChiService) {
        this.tieuChiService = tieuChiService;
    }

    /**
     * POST  /tieu-chis : Create a new tieuChi.
     *
     * @param tieuChiDTO the tieuChiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tieuChiDTO, or with status 400 (Bad Request) if the tieuChi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tieu-chis")
    public ResponseEntity<TieuChiDTO> createTieuChi(@Valid @RequestBody TieuChiDTO tieuChiDTO) throws URISyntaxException {
        log.debug("REST request to save TieuChi : {}", tieuChiDTO);
        if (tieuChiDTO.getId() != null) {
            throw new BadRequestAlertException("A new tieuChi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TieuChiDTO result = tieuChiService.save(tieuChiDTO);
        return ResponseEntity.created(new URI("/api/tieu-chis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tieu-chis : Updates an existing tieuChi.
     *
     * @param tieuChiDTO the tieuChiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tieuChiDTO,
     * or with status 400 (Bad Request) if the tieuChiDTO is not valid,
     * or with status 500 (Internal Server Error) if the tieuChiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tieu-chis")
    public ResponseEntity<TieuChiDTO> updateTieuChi(@Valid @RequestBody TieuChiDTO tieuChiDTO) throws URISyntaxException {
        log.debug("REST request to update TieuChi : {}", tieuChiDTO);
        if (tieuChiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TieuChiDTO result = tieuChiService.save(tieuChiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tieuChiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tieu-chis : get all the tieuChis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tieuChis in body
     */
    @GetMapping("/tieu-chis")
    public ResponseEntity<List<TieuChiDTO>> getAllTieuChis(Pageable pageable) {
        log.debug("REST request to get a page of TieuChis");
        Page<TieuChiDTO> page = tieuChiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tieu-chis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tieu-chis : get all the tieuChis.
     *
     * @param id the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tieuChis in body
     */
    @GetMapping("/tieu-chis/co_quan_chu_tri")
    public ResponseEntity<List<TieuChiDetailDTO>> getTieuChisByCoQuanChuTriID(@RequestParam("id") Long id) {
        log.debug("REST request to get a page of TieuChis");
        List<TieuChiDetailDTO> page = tieuChiService.findByCoQuanChuTriId(id);
        return ResponseEntity.ok().body(page);
    }

    /**
     * GET  /tieu-chis/:id : get the "id" tieuChi.
     *
     * @param id the id of the tieuChiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tieuChiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tieu-chis/{id}")
    public ResponseEntity<TieuChiDTO> getTieuChi(@PathVariable Long id) {
        log.debug("REST request to get TieuChi : {}", id);
        Optional<TieuChiDTO> tieuChiDTO = tieuChiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tieuChiDTO);
    }

    /**
     * DELETE  /tieu-chis/:id : delete the "id" tieuChi.
     *
     * @param id the id of the tieuChiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tieu-chis/{id}")
    public ResponseEntity<Void> deleteTieuChi(@PathVariable Long id) {
        log.debug("REST request to delete TieuChi : {}", id);
        tieuChiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
