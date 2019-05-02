package com.manager.common.web.rest;

import com.manager.common.CommonApp;
import com.manager.common.domain.ChiTieu;
import com.manager.common.domain.enumeration.Status;
import com.manager.common.repository.ChiTieuRepository;
import com.manager.common.service.ChiTieuService;
import com.manager.common.service.dto.ChiTieuDTO;
import com.manager.common.service.mapper.ChiTieuMapper;
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
 * Test class for the ChiTieuResource REST controller.
 *
 * @see ChiTieuResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class ChiTieuResourceIntTest {

    private static final String DEFAULT_CHI_TIEU_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CHI_TIEU_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.PUBLISH;
    private static final Status UPDATED_STATUS = Status.UNPUBLISH;

    @Autowired
    private ChiTieuRepository chiTieuRepository;

    @Autowired
    private ChiTieuMapper chiTieuMapper;

    @Autowired
    private ChiTieuService chiTieuService;

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

    private MockMvc restChiTieuMockMvc;

    private ChiTieu chiTieu;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChiTieuResource chiTieuResource = new ChiTieuResource(chiTieuService);
        this.restChiTieuMockMvc = MockMvcBuilders.standaloneSetup(chiTieuResource)
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
    public static ChiTieu createEntity(EntityManager em) {
        ChiTieu chiTieu = new ChiTieu()
            .chiTieuCode(DEFAULT_CHI_TIEU_CODE)
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS);
        return chiTieu;
    }

    @Before
    public void initTest() {
        chiTieu = createEntity(em);
    }

    @Test
    @Transactional
    public void createChiTieu() throws Exception {
        int databaseSizeBeforeCreate = chiTieuRepository.findAll().size();

        // Create the ChiTieu
        ChiTieuDTO chiTieuDTO = chiTieuMapper.toDto(chiTieu);
        restChiTieuMockMvc.perform(post("/api/chi-tieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chiTieuDTO)))
            .andExpect(status().isCreated());

        // Validate the ChiTieu in the database
        List<ChiTieu> chiTieuList = chiTieuRepository.findAll();
        assertThat(chiTieuList).hasSize(databaseSizeBeforeCreate + 1);
        ChiTieu testChiTieu = chiTieuList.get(chiTieuList.size() - 1);
        assertThat(testChiTieu.getChiTieuCode()).isEqualTo(DEFAULT_CHI_TIEU_CODE);
        assertThat(testChiTieu.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testChiTieu.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createChiTieuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chiTieuRepository.findAll().size();

        // Create the ChiTieu with an existing ID
        chiTieu.setId(1L);
        ChiTieuDTO chiTieuDTO = chiTieuMapper.toDto(chiTieu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChiTieuMockMvc.perform(post("/api/chi-tieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chiTieuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChiTieu in the database
        List<ChiTieu> chiTieuList = chiTieuRepository.findAll();
        assertThat(chiTieuList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkChiTieuCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = chiTieuRepository.findAll().size();
        // set the field null
        chiTieu.setChiTieuCode(null);

        // Create the ChiTieu, which fails.
        ChiTieuDTO chiTieuDTO = chiTieuMapper.toDto(chiTieu);

        restChiTieuMockMvc.perform(post("/api/chi-tieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chiTieuDTO)))
            .andExpect(status().isBadRequest());

        List<ChiTieu> chiTieuList = chiTieuRepository.findAll();
        assertThat(chiTieuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = chiTieuRepository.findAll().size();
        // set the field null
        chiTieu.setName(null);

        // Create the ChiTieu, which fails.
        ChiTieuDTO chiTieuDTO = chiTieuMapper.toDto(chiTieu);

        restChiTieuMockMvc.perform(post("/api/chi-tieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chiTieuDTO)))
            .andExpect(status().isBadRequest());

        List<ChiTieu> chiTieuList = chiTieuRepository.findAll();
        assertThat(chiTieuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = chiTieuRepository.findAll().size();
        // set the field null
        chiTieu.setStatus(null);

        // Create the ChiTieu, which fails.
        ChiTieuDTO chiTieuDTO = chiTieuMapper.toDto(chiTieu);

        restChiTieuMockMvc.perform(post("/api/chi-tieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chiTieuDTO)))
            .andExpect(status().isBadRequest());

        List<ChiTieu> chiTieuList = chiTieuRepository.findAll();
        assertThat(chiTieuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChiTieus() throws Exception {
        // Initialize the database
        chiTieuRepository.saveAndFlush(chiTieu);

        // Get all the chiTieuList
        restChiTieuMockMvc.perform(get("/api/chi-tieus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chiTieu.getId().intValue())))
            .andExpect(jsonPath("$.[*].chiTieuCode").value(hasItem(DEFAULT_CHI_TIEU_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getChiTieu() throws Exception {
        // Initialize the database
        chiTieuRepository.saveAndFlush(chiTieu);

        // Get the chiTieu
        restChiTieuMockMvc.perform(get("/api/chi-tieus/{id}", chiTieu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chiTieu.getId().intValue()))
            .andExpect(jsonPath("$.chiTieuCode").value(DEFAULT_CHI_TIEU_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingChiTieu() throws Exception {
        // Get the chiTieu
        restChiTieuMockMvc.perform(get("/api/chi-tieus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChiTieu() throws Exception {
        // Initialize the database
        chiTieuRepository.saveAndFlush(chiTieu);

        int databaseSizeBeforeUpdate = chiTieuRepository.findAll().size();

        // Update the chiTieu
        ChiTieu updatedChiTieu = chiTieuRepository.findById(chiTieu.getId()).get();
        // Disconnect from session so that the updates on updatedChiTieu are not directly saved in db
        em.detach(updatedChiTieu);
        updatedChiTieu
            .chiTieuCode(UPDATED_CHI_TIEU_CODE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        ChiTieuDTO chiTieuDTO = chiTieuMapper.toDto(updatedChiTieu);

        restChiTieuMockMvc.perform(put("/api/chi-tieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chiTieuDTO)))
            .andExpect(status().isOk());

        // Validate the ChiTieu in the database
        List<ChiTieu> chiTieuList = chiTieuRepository.findAll();
        assertThat(chiTieuList).hasSize(databaseSizeBeforeUpdate);
        ChiTieu testChiTieu = chiTieuList.get(chiTieuList.size() - 1);
        assertThat(testChiTieu.getChiTieuCode()).isEqualTo(UPDATED_CHI_TIEU_CODE);
        assertThat(testChiTieu.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testChiTieu.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingChiTieu() throws Exception {
        int databaseSizeBeforeUpdate = chiTieuRepository.findAll().size();

        // Create the ChiTieu
        ChiTieuDTO chiTieuDTO = chiTieuMapper.toDto(chiTieu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiTieuMockMvc.perform(put("/api/chi-tieus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chiTieuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChiTieu in the database
        List<ChiTieu> chiTieuList = chiTieuRepository.findAll();
        assertThat(chiTieuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChiTieu() throws Exception {
        // Initialize the database
        chiTieuRepository.saveAndFlush(chiTieu);

        int databaseSizeBeforeDelete = chiTieuRepository.findAll().size();

        // Delete the chiTieu
        restChiTieuMockMvc.perform(delete("/api/chi-tieus/{id}", chiTieu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ChiTieu> chiTieuList = chiTieuRepository.findAll();
        assertThat(chiTieuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTieu.class);
        ChiTieu chiTieu1 = new ChiTieu();
        chiTieu1.setId(1L);
        ChiTieu chiTieu2 = new ChiTieu();
        chiTieu2.setId(chiTieu1.getId());
        assertThat(chiTieu1).isEqualTo(chiTieu2);
        chiTieu2.setId(2L);
        assertThat(chiTieu1).isNotEqualTo(chiTieu2);
        chiTieu1.setId(null);
        assertThat(chiTieu1).isNotEqualTo(chiTieu2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTieuDTO.class);
        ChiTieuDTO chiTieuDTO1 = new ChiTieuDTO();
        chiTieuDTO1.setId(1L);
        ChiTieuDTO chiTieuDTO2 = new ChiTieuDTO();
        assertThat(chiTieuDTO1).isNotEqualTo(chiTieuDTO2);
        chiTieuDTO2.setId(chiTieuDTO1.getId());
        assertThat(chiTieuDTO1).isEqualTo(chiTieuDTO2);
        chiTieuDTO2.setId(2L);
        assertThat(chiTieuDTO1).isNotEqualTo(chiTieuDTO2);
        chiTieuDTO1.setId(null);
        assertThat(chiTieuDTO1).isNotEqualTo(chiTieuDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(chiTieuMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(chiTieuMapper.fromId(null)).isNull();
    }
}
