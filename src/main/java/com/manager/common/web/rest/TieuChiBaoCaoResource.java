package com.manager.common.web.rest;
import com.manager.common.service.TieuChiBaoCaoService;
import com.manager.common.web.rest.errors.BadRequestAlertException;
import com.manager.common.web.rest.util.HeaderUtil;
import com.manager.common.web.rest.util.PaginationUtil;
import com.manager.common.service.dto.TieuChiBaoCaoDTO;
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
 * REST controller for managing TieuChiBaoCao.
 */
@RestController
@RequestMapping("/api")
public class TieuChiBaoCaoResource {

    private final Logger log = LoggerFactory.getLogger(TieuChiBaoCaoResource.class);

    private static final String ENTITY_NAME = "commonTieuChiBaoCao";

    private final TieuChiBaoCaoService tieuChiBaoCaoService;

    public TieuChiBaoCaoResource(TieuChiBaoCaoService tieuChiBaoCaoService) {
        this.tieuChiBaoCaoService = tieuChiBaoCaoService;
    }

    /**
     * POST  /tieu-chi-bao-caos : Create a new tieuChiBaoCao.
     *
     * @param tieuChiBaoCaoDTO the tieuChiBaoCaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tieuChiBaoCaoDTO, or with status 400 (Bad Request) if the tieuChiBaoCao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tieu-chi-bao-caos")
    public ResponseEntity<TieuChiBaoCaoDTO> createTieuChiBaoCao(@Valid @RequestBody TieuChiBaoCaoDTO tieuChiBaoCaoDTO) throws URISyntaxException {
        log.debug("REST request to save TieuChiBaoCao : {}", tieuChiBaoCaoDTO);
        if (tieuChiBaoCaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tieuChiBaoCao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TieuChiBaoCaoDTO result = tieuChiBaoCaoService.save(tieuChiBaoCaoDTO);
        return ResponseEntity.created(new URI("/api/tieu-chi-bao-caos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tieu-chi-bao-caos : Updates an existing tieuChiBaoCao.
     *
     * @param tieuChiBaoCaoDTO the tieuChiBaoCaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tieuChiBaoCaoDTO,
     * or with status 400 (Bad Request) if the tieuChiBaoCaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the tieuChiBaoCaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tieu-chi-bao-caos")
    public ResponseEntity<TieuChiBaoCaoDTO> updateTieuChiBaoCao(@Valid @RequestBody TieuChiBaoCaoDTO tieuChiBaoCaoDTO) throws URISyntaxException {
        log.debug("REST request to update TieuChiBaoCao : {}", tieuChiBaoCaoDTO);
        if (tieuChiBaoCaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TieuChiBaoCaoDTO result = tieuChiBaoCaoService.save(tieuChiBaoCaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tieuChiBaoCaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tieu-chi-bao-caos : get all the tieuChiBaoCaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tieuChiBaoCaos in body
     */
    @GetMapping("/tieu-chi-bao-caos")
    public ResponseEntity<List<TieuChiBaoCaoDTO>> getAllTieuChiBaoCaos(Pageable pageable) {
        log.debug("REST request to get a page of TieuChiBaoCaos");
        Page<TieuChiBaoCaoDTO> page = tieuChiBaoCaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tieu-chi-bao-caos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tieu-chi-bao-caos/:id : get the "id" tieuChiBaoCao.
     *
     * @param id the id of the tieuChiBaoCaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tieuChiBaoCaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tieu-chi-bao-caos/{id}")
    public ResponseEntity<TieuChiBaoCaoDTO> getTieuChiBaoCao(@PathVariable Long id) {
        log.debug("REST request to get TieuChiBaoCao : {}", id);
        Optional<TieuChiBaoCaoDTO> tieuChiBaoCaoDTO = tieuChiBaoCaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tieuChiBaoCaoDTO);
    }

    /**
     * DELETE  /tieu-chi-bao-caos/:id : delete the "id" tieuChiBaoCao.
     *
     * @param id the id of the tieuChiBaoCaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tieu-chi-bao-caos/{id}")
    public ResponseEntity<Void> deleteTieuChiBaoCao(@PathVariable Long id) {
        log.debug("REST request to delete TieuChiBaoCao : {}", id);
        tieuChiBaoCaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
