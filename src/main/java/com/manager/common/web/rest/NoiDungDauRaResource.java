package com.manager.common.web.rest;

import com.manager.common.service.NoiDungDauRaService;
import com.manager.common.service.dto.NoiDungDauRaDTO;
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
 * REST controller for managing NoiDungDauRa.
 */
@RestController
@RequestMapping("/api")
public class NoiDungDauRaResource {

    private final Logger log = LoggerFactory.getLogger(NoiDungDauRaResource.class);

    private static final String ENTITY_NAME = "commonNoiDungDauRa";

    private final NoiDungDauRaService noiDungDauRaService;

    public NoiDungDauRaResource(NoiDungDauRaService noiDungDauRaService) {
        this.noiDungDauRaService = noiDungDauRaService;
    }

    /**
     * POST  /noi-dung-dau-ras : Create a new noiDungDauRa.
     *
     * @param noiDungDauRaDTO the noiDungDauRaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new noiDungDauRaDTO, or with status 400 (Bad Request) if the noiDungDauRa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/noi-dung-dau-ras")
    public ResponseEntity<NoiDungDauRaDTO> createNoiDungDauRa(@Valid @RequestBody NoiDungDauRaDTO noiDungDauRaDTO) throws URISyntaxException {
        log.debug("REST request to save NoiDungDauRa : {}", noiDungDauRaDTO);
        if (noiDungDauRaDTO.getId() != null) {
            throw new BadRequestAlertException("A new noiDungDauRa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoiDungDauRaDTO result = noiDungDauRaService.save(noiDungDauRaDTO);
        return ResponseEntity.created(new URI("/api/noi-dung-dau-ras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /noi-dung-dau-ras : Updates an existing noiDungDauRa.
     *
     * @param noiDungDauRaDTO the noiDungDauRaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated noiDungDauRaDTO,
     * or with status 400 (Bad Request) if the noiDungDauRaDTO is not valid,
     * or with status 500 (Internal Server Error) if the noiDungDauRaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/noi-dung-dau-ras")
    public ResponseEntity<NoiDungDauRaDTO> updateNoiDungDauRa(@Valid @RequestBody NoiDungDauRaDTO noiDungDauRaDTO) throws URISyntaxException {
        log.debug("REST request to update NoiDungDauRa : {}", noiDungDauRaDTO);
        if (noiDungDauRaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NoiDungDauRaDTO result = noiDungDauRaService.save(noiDungDauRaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, noiDungDauRaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /noi-dung-dau-ras : get all the noiDungDauRas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of noiDungDauRas in body
     */
    @GetMapping("/noi-dung-dau-ras")
    public ResponseEntity<List<NoiDungDauRaDTO>> getAllNoiDungDauRas(Pageable pageable) {
        log.debug("REST request to get a page of NoiDungDauRas");
        Page<NoiDungDauRaDTO> page = noiDungDauRaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/noi-dung-dau-ras");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /noi-dung-dau-ras/:id : get the "id" noiDungDauRa.
     *
     * @param id the id of the noiDungDauRaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the noiDungDauRaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/noi-dung-dau-ras/{id}")
    public ResponseEntity<NoiDungDauRaDTO> getNoiDungDauRa(@PathVariable Long id) {
        log.debug("REST request to get NoiDungDauRa : {}", id);
        Optional<NoiDungDauRaDTO> noiDungDauRaDTO = noiDungDauRaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noiDungDauRaDTO);
    }

    /**
     * DELETE  /noi-dung-dau-ras/:id : delete the "id" noiDungDauRa.
     *
     * @param id the id of the noiDungDauRaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/noi-dung-dau-ras/{id}")
    public ResponseEntity<Void> deleteNoiDungDauRa(@PathVariable Long id) {
        log.debug("REST request to delete NoiDungDauRa : {}", id);
        noiDungDauRaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
