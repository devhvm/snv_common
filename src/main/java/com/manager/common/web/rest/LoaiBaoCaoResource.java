package com.manager.common.web.rest;

import com.manager.common.service.LoaiBaoCaoService;
import com.manager.common.service.NhomPhanLoaiService;
import com.manager.common.service.dto.NhomPhanLoaiDTO;
import com.manager.common.service.dto.loaibaocao.LoaiBaoCaoDTO;
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
 * REST controller for managing loaiBaoCao.
 */
@RestController
@RequestMapping("/api")
public class LoaiBaoCaoResource {

    private final Logger log = LoggerFactory.getLogger(LoaiBaoCaoResource.class);

    private static final String ENTITY_NAME = "commonLoaiBaoCao";

    private final LoaiBaoCaoService loaiBaoCaoService;

    public LoaiBaoCaoResource(LoaiBaoCaoService loaiBaoCaoService) {
        this.loaiBaoCaoService = loaiBaoCaoService;
    }

    /**
     * GET  /loai-bao-caos : get all the loaiBaoCaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loaiBaoCaos in body
     */
    @GetMapping("/loai-bao-caos")
    public ResponseEntity<List<LoaiBaoCaoDTO>> getAllLoaiBaoCaos(Pageable pageable) {
        log.debug("REST request to get a page of LoaiBaoCaos");
        Page<LoaiBaoCaoDTO> page = loaiBaoCaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loai-bao-caos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /loai-bao-caos/:id : get the "id" loaiBaoCao.
     *
     * @param id the id of the loaiBaoCaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loaiBaoCaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/loai-bao-caos/{id}")
    public ResponseEntity<LoaiBaoCaoDTO> getLoaiBaoCao(@PathVariable Long id) {
        log.debug("REST request to get loaiBaoCaoDTO : {}", id);
        Optional<LoaiBaoCaoDTO> loaiBaoCaoDTO = loaiBaoCaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loaiBaoCaoDTO);
    }
}
