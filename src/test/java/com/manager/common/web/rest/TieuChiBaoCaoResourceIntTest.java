package com.manager.common.web.rest;

import com.manager.common.CommonApp;
import com.manager.common.domain.TieuChiBaoCao;
import com.manager.common.domain.enumeration.Status;
import com.manager.common.repository.TieuChiBaoCaoRepository;
import com.manager.common.service.TieuChiBaoCaoService;
import com.manager.common.service.dto.TieuChiBaoCaoDTO;
import com.manager.common.service.mapper.TieuChiBaoCaoMapper;
import com.manager.common.web.rest.errors.ExceptionTranslator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.manager.common.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Test class for the TieuChiBaoCaoResource REST controller.
 *
 * @see TieuChiBaoCaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class TieuChiBaoCaoResourceIntTest {

    private static final Status DEFAULT_STATUS = Status.PUBLISH;
    private static final Status UPDATED_STATUS = Status.UNPUBLISH;

    @Autowired
    private TieuChiBaoCaoRepository tieuChiBaoCaoRepository;

    @Autowired
    private TieuChiBaoCaoMapper tieuChiBaoCaoMapper;

    @Autowired
    private TieuChiBaoCaoService tieuChiBaoCaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTieuChiBaoCaoMockMvc;

    private TieuChiBaoCao tieuChiBaoCao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TieuChiBaoCaoResource tieuChiBaoCaoResource = new TieuChiBaoCaoResource(tieuChiBaoCaoService);
        this.restTieuChiBaoCaoMockMvc = MockMvcBuilders.standaloneSetup(tieuChiBaoCaoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TieuChiBaoCao createEntity(EntityManager em) {
        TieuChiBaoCao tieuChiBaoCao = new TieuChiBaoCao()
            .status(DEFAULT_STATUS);
        return tieuChiBaoCao;
    }

    @Before
    public void initTest() {
        tieuChiBaoCao = createEntity(em);
    }

    @Test
    @Transactional
    public void createTieuChiBaoCao() throws Exception {
        int databaseSizeBeforeCreate = tieuChiBaoCaoRepository.findAll().size();

        // Create the TieuChiBaoCao
        TieuChiBaoCaoDTO tieuChiBaoCaoDTO = tieuChiBaoCaoMapper.toDto(tieuChiBaoCao);
        restTieuChiBaoCaoMockMvc.perform(post("/api/tieu-chi-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tieuChiBaoCaoDTO)))
            .andExpect(status().isCreated());

        // Validate the TieuChiBaoCao in the database
        List<TieuChiBaoCao> tieuChiBaoCaoList = tieuChiBaoCaoRepository.findAll();
        assertThat(tieuChiBaoCaoList).hasSize(databaseSizeBeforeCreate + 1);
        TieuChiBaoCao testTieuChiBaoCao = tieuChiBaoCaoList.get(tieuChiBaoCaoList.size() - 1);
        assertThat(testTieuChiBaoCao.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createTieuChiBaoCaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tieuChiBaoCaoRepository.findAll().size();

        // Create the TieuChiBaoCao with an existing ID
        tieuChiBaoCao.setId(1L);
        TieuChiBaoCaoDTO tieuChiBaoCaoDTO = tieuChiBaoCaoMapper.toDto(tieuChiBaoCao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTieuChiBaoCaoMockMvc.perform(post("/api/tieu-chi-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tieuChiBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TieuChiBaoCao in the database
        List<TieuChiBaoCao> tieuChiBaoCaoList = tieuChiBaoCaoRepository.findAll();
        assertThat(tieuChiBaoCaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = tieuChiBaoCaoRepository.findAll().size();
        // set the field null
        tieuChiBaoCao.setStatus(null);

        // Create the TieuChiBaoCao, which fails.
        TieuChiBaoCaoDTO tieuChiBaoCaoDTO = tieuChiBaoCaoMapper.toDto(tieuChiBaoCao);

        restTieuChiBaoCaoMockMvc.perform(post("/api/tieu-chi-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tieuChiBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        List<TieuChiBaoCao> tieuChiBaoCaoList = tieuChiBaoCaoRepository.findAll();
        assertThat(tieuChiBaoCaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTieuChiBaoCaos() throws Exception {
        // Initialize the database
        tieuChiBaoCaoRepository.saveAndFlush(tieuChiBaoCao);

        // Get all the tieuChiBaoCaoList
        restTieuChiBaoCaoMockMvc.perform(get("/api/tieu-chi-bao-caos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tieuChiBaoCao.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getTieuChiBaoCao() throws Exception {
        // Initialize the database
        tieuChiBaoCaoRepository.saveAndFlush(tieuChiBaoCao);

        // Get the tieuChiBaoCao
        restTieuChiBaoCaoMockMvc.perform(get("/api/tieu-chi-bao-caos/{id}", tieuChiBaoCao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tieuChiBaoCao.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTieuChiBaoCao() throws Exception {
        // Get the tieuChiBaoCao
        restTieuChiBaoCaoMockMvc.perform(get("/api/tieu-chi-bao-caos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTieuChiBaoCao() throws Exception {
        // Initialize the database
        tieuChiBaoCaoRepository.saveAndFlush(tieuChiBaoCao);

        int databaseSizeBeforeUpdate = tieuChiBaoCaoRepository.findAll().size();

        // Update the tieuChiBaoCao
        TieuChiBaoCao updatedTieuChiBaoCao = tieuChiBaoCaoRepository.findById(tieuChiBaoCao.getId()).get();
        // Disconnect from session so that the updates on updatedTieuChiBaoCao are not directly saved in db
        em.detach(updatedTieuChiBaoCao);
        updatedTieuChiBaoCao
            .status(UPDATED_STATUS);
        TieuChiBaoCaoDTO tieuChiBaoCaoDTO = tieuChiBaoCaoMapper.toDto(updatedTieuChiBaoCao);

        restTieuChiBaoCaoMockMvc.perform(put("/api/tieu-chi-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tieuChiBaoCaoDTO)))
            .andExpect(status().isOk());

        // Validate the TieuChiBaoCao in the database
        List<TieuChiBaoCao> tieuChiBaoCaoList = tieuChiBaoCaoRepository.findAll();
        assertThat(tieuChiBaoCaoList).hasSize(databaseSizeBeforeUpdate);
        TieuChiBaoCao testTieuChiBaoCao = tieuChiBaoCaoList.get(tieuChiBaoCaoList.size() - 1);
        assertThat(testTieuChiBaoCao.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingTieuChiBaoCao() throws Exception {
        int databaseSizeBeforeUpdate = tieuChiBaoCaoRepository.findAll().size();

        // Create the TieuChiBaoCao
        TieuChiBaoCaoDTO tieuChiBaoCaoDTO = tieuChiBaoCaoMapper.toDto(tieuChiBaoCao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTieuChiBaoCaoMockMvc.perform(put("/api/tieu-chi-bao-caos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tieuChiBaoCaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TieuChiBaoCao in the database
        List<TieuChiBaoCao> tieuChiBaoCaoList = tieuChiBaoCaoRepository.findAll();
        assertThat(tieuChiBaoCaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTieuChiBaoCao() throws Exception {
        // Initialize the database
        tieuChiBaoCaoRepository.saveAndFlush(tieuChiBaoCao);

        int databaseSizeBeforeDelete = tieuChiBaoCaoRepository.findAll().size();

        // Delete the tieuChiBaoCao
        restTieuChiBaoCaoMockMvc.perform(delete("/api/tieu-chi-bao-caos/{id}", tieuChiBaoCao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TieuChiBaoCao> tieuChiBaoCaoList = tieuChiBaoCaoRepository.findAll();
        assertThat(tieuChiBaoCaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TieuChiBaoCao.class);
        TieuChiBaoCao tieuChiBaoCao1 = new TieuChiBaoCao();
        tieuChiBaoCao1.setId(1L);
        TieuChiBaoCao tieuChiBaoCao2 = new TieuChiBaoCao();
        tieuChiBaoCao2.setId(tieuChiBaoCao1.getId());
        assertThat(tieuChiBaoCao1).isEqualTo(tieuChiBaoCao2);
        tieuChiBaoCao2.setId(2L);
        assertThat(tieuChiBaoCao1).isNotEqualTo(tieuChiBaoCao2);
        tieuChiBaoCao1.setId(null);
        assertThat(tieuChiBaoCao1).isNotEqualTo(tieuChiBaoCao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TieuChiBaoCaoDTO.class);
        TieuChiBaoCaoDTO tieuChiBaoCaoDTO1 = new TieuChiBaoCaoDTO();
        tieuChiBaoCaoDTO1.setId(1L);
        TieuChiBaoCaoDTO tieuChiBaoCaoDTO2 = new TieuChiBaoCaoDTO();
        assertThat(tieuChiBaoCaoDTO1).isNotEqualTo(tieuChiBaoCaoDTO2);
        tieuChiBaoCaoDTO2.setId(tieuChiBaoCaoDTO1.getId());
        assertThat(tieuChiBaoCaoDTO1).isEqualTo(tieuChiBaoCaoDTO2);
        tieuChiBaoCaoDTO2.setId(2L);
        assertThat(tieuChiBaoCaoDTO1).isNotEqualTo(tieuChiBaoCaoDTO2);
        tieuChiBaoCaoDTO1.setId(null);
        assertThat(tieuChiBaoCaoDTO1).isNotEqualTo(tieuChiBaoCaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tieuChiBaoCaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tieuChiBaoCaoMapper.fromId(null)).isNull();
    }
}
