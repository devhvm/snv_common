package com.manager.common.web.rest;

import com.manager.common.service.NhomPhanLoaiService;
import com.manager.common.service.dto.NhomPhanLoaiDTO;
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
 * REST controller for managing NhomPhanLoai.
 */
@RestController
@RequestMapping("/api")
public class NhomPhanLoaiResource {

    private final Logger log = LoggerFactory.getLogger(NhomPhanLoaiResource.class);

    private static final String ENTITY_NAME = "commonNhomPhanLoai";

    private final NhomPhanLoaiService nhomPhanLoaiService;

    public NhomPhanLoaiResource(NhomPhanLoaiService nhomPhanLoaiService) {
        this.nhomPhanLoaiService = nhomPhanLoaiService;
    }

    /**
     * POST  /nhom-phan-loais : Create a new nhomPhanLoai.
     *
     * @param nhomPhanLoaiDTO the nhomPhanLoaiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nhomPhanLoaiDTO, or with status 400 (Bad Request) if the nhomPhanLoai has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nhom-phan-loais")
    public ResponseEntity<NhomPhanLoaiDTO> createNhomPhanLoai(@Valid @RequestBody NhomPhanLoaiDTO nhomPhanLoaiDTO) throws URISyntaxException {
        log.debug("REST request to save NhomPhanLoai : {}", nhomPhanLoaiDTO);
        if (nhomPhanLoaiDTO.getId() != null) {
            throw new BadRequestAlertException("A new nhomPhanLoai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NhomPhanLoaiDTO result = nhomPhanLoaiService.save(nhomPhanLoaiDTO);
        return ResponseEntity.created(new URI("/api/nhom-phan-loais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nhom-phan-loais : Updates an existing nhomPhanLoai.
     *
     * @param nhomPhanLoaiDTO the nhomPhanLoaiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nhomPhanLoaiDTO,
     * or with status 400 (Bad Request) if the nhomPhanLoaiDTO is not valid,
     * or with status 500 (Internal Server Error) if the nhomPhanLoaiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nhom-phan-loais")
    public ResponseEntity<NhomPhanLoaiDTO> updateNhomPhanLoai(@Valid @RequestBody NhomPhanLoaiDTO nhomPhanLoaiDTO) throws URISyntaxException {
        log.debug("REST request to update NhomPhanLoai : {}", nhomPhanLoaiDTO);
        if (nhomPhanLoaiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NhomPhanLoaiDTO result = nhomPhanLoaiService.save(nhomPhanLoaiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nhomPhanLoaiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nhom-phan-loais : get all the nhomPhanLoais.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of nhomPhanLoais in body
     */
    @GetMapping("/nhom-phan-loais")
    public ResponseEntity<List<NhomPhanLoaiDTO>> getAllNhomPhanLoais(Pageable pageable) {
        log.debug("REST request to get a page of NhomPhanLoais");
        Page<NhomPhanLoaiDTO> page = nhomPhanLoaiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nhom-phan-loais");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /nhom-phan-loais/:id : get the "id" nhomPhanLoai.
     *
     * @param id the id of the nhomPhanLoaiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nhomPhanLoaiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nhom-phan-loais/{id}")
    public ResponseEntity<NhomPhanLoaiDTO> getNhomPhanLoai(@PathVariable Long id) {
        log.debug("REST request to get NhomPhanLoai : {}", id);
        Optional<NhomPhanLoaiDTO> nhomPhanLoaiDTO = nhomPhanLoaiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nhomPhanLoaiDTO);
    }

    /**
     * DELETE  /nhom-phan-loais/:id : delete the "id" nhomPhanLoai.
     *
     * @param id the id of the nhomPhanLoaiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nhom-phan-loais/{id}")
    public ResponseEntity<Void> deleteNhomPhanLoai(@PathVariable Long id) {
        log.debug("REST request to delete NhomPhanLoai : {}", id);
        nhomPhanLoaiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
