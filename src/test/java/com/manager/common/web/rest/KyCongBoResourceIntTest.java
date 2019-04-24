package com.manager.common.web.rest;

import com.manager.common.CommonApp;

import com.manager.common.domain.KyCongBo;
import com.manager.common.repository.KyCongBoRepository;
import com.manager.common.service.KyCongBoService;
import com.manager.common.service.dto.KyCongBoDTO;
import com.manager.common.service.mapper.KyCongBoMapper;
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

import com.manager.common.domain.enumeration.Status;
/**
 * Test class for the KyCongBoResource REST controller.
 *
 * @see KyCongBoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class KyCongBoResourceIntTest {

    private static final String DEFAULT_KY_CONG_BO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_KY_CONG_BO_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.PUBLISH;
    private static final Status UPDATED_STATUS = Status.UNPUBLISH;

    @Autowired
    private KyCongBoRepository kyCongBoRepository;

    @Autowired
    private KyCongBoMapper kyCongBoMapper;

    @Autowired
    private KyCongBoService kyCongBoService;

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

    private MockMvc restKyCongBoMockMvc;

    private KyCongBo kyCongBo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KyCongBoResource kyCongBoResource = new KyCongBoResource(kyCongBoService);
        this.restKyCongBoMockMvc = MockMvcBuilders.standaloneSetup(kyCongBoResource)
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
    public static KyCongBo createEntity(EntityManager em) {
        KyCongBo kyCongBo = new KyCongBo()
            .kyCongBoCode(DEFAULT_KY_CONG_BO_CODE)
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS);
        return kyCongBo;
    }

    @Before
    public void initTest() {
        kyCongBo = createEntity(em);
    }

    @Test
    @Transactional
    public void createKyCongBo() throws Exception {
        int databaseSizeBeforeCreate = kyCongBoRepository.findAll().size();

        // Create the KyCongBo
        KyCongBoDTO kyCongBoDTO = kyCongBoMapper.toDto(kyCongBo);
        restKyCongBoMockMvc.perform(post("/api/ky-cong-bos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kyCongBoDTO)))
            .andExpect(status().isCreated());

        // Validate the KyCongBo in the database
        List<KyCongBo> kyCongBoList = kyCongBoRepository.findAll();
        assertThat(kyCongBoList).hasSize(databaseSizeBeforeCreate + 1);
        KyCongBo testKyCongBo = kyCongBoList.get(kyCongBoList.size() - 1);
        assertThat(testKyCongBo.getKyCongBoCode()).isEqualTo(DEFAULT_KY_CONG_BO_CODE);
        assertThat(testKyCongBo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testKyCongBo.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createKyCongBoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kyCongBoRepository.findAll().size();

        // Create the KyCongBo with an existing ID
        kyCongBo.setId(1L);
        KyCongBoDTO kyCongBoDTO = kyCongBoMapper.toDto(kyCongBo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKyCongBoMockMvc.perform(post("/api/ky-cong-bos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kyCongBoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KyCongBo in the database
        List<KyCongBo> kyCongBoList = kyCongBoRepository.findAll();
        assertThat(kyCongBoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkKyCongBoCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kyCongBoRepository.findAll().size();
        // set the field null
        kyCongBo.setKyCongBoCode(null);

        // Create the KyCongBo, which fails.
        KyCongBoDTO kyCongBoDTO = kyCongBoMapper.toDto(kyCongBo);

        restKyCongBoMockMvc.perform(post("/api/ky-cong-bos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kyCongBoDTO)))
            .andExpect(status().isBadRequest());

        List<KyCongBo> kyCongBoList = kyCongBoRepository.findAll();
        assertThat(kyCongBoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = kyCongBoRepository.findAll().size();
        // set the field null
        kyCongBo.setName(null);

        // Create the KyCongBo, which fails.
        KyCongBoDTO kyCongBoDTO = kyCongBoMapper.toDto(kyCongBo);

        restKyCongBoMockMvc.perform(post("/api/ky-cong-bos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kyCongBoDTO)))
            .andExpect(status().isBadRequest());

        List<KyCongBo> kyCongBoList = kyCongBoRepository.findAll();
        assertThat(kyCongBoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = kyCongBoRepository.findAll().size();
        // set the field null
        kyCongBo.setStatus(null);

        // Create the KyCongBo, which fails.
        KyCongBoDTO kyCongBoDTO = kyCongBoMapper.toDto(kyCongBo);

        restKyCongBoMockMvc.perform(post("/api/ky-cong-bos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kyCongBoDTO)))
            .andExpect(status().isBadRequest());

        List<KyCongBo> kyCongBoList = kyCongBoRepository.findAll();
        assertThat(kyCongBoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKyCongBos() throws Exception {
        // Initialize the database
        kyCongBoRepository.saveAndFlush(kyCongBo);

        // Get all the kyCongBoList
        restKyCongBoMockMvc.perform(get("/api/ky-cong-bos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kyCongBo.getId().intValue())))
            .andExpect(jsonPath("$.[*].kyCongBoCode").value(hasItem(DEFAULT_KY_CONG_BO_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getKyCongBo() throws Exception {
        // Initialize the database
        kyCongBoRepository.saveAndFlush(kyCongBo);

        // Get the kyCongBo
        restKyCongBoMockMvc.perform(get("/api/ky-cong-bos/{id}", kyCongBo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(kyCongBo.getId().intValue()))
            .andExpect(jsonPath("$.kyCongBoCode").value(DEFAULT_KY_CONG_BO_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKyCongBo() throws Exception {
        // Get the kyCongBo
        restKyCongBoMockMvc.perform(get("/api/ky-cong-bos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKyCongBo() throws Exception {
        // Initialize the database
        kyCongBoRepository.saveAndFlush(kyCongBo);

        int databaseSizeBeforeUpdate = kyCongBoRepository.findAll().size();

        // Update the kyCongBo
        KyCongBo updatedKyCongBo = kyCongBoRepository.findById(kyCongBo.getId()).get();
        // Disconnect from session so that the updates on updatedKyCongBo are not directly saved in db
        em.detach(updatedKyCongBo);
        updatedKyCongBo
            .kyCongBoCode(UPDATED_KY_CONG_BO_CODE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        KyCongBoDTO kyCongBoDTO = kyCongBoMapper.toDto(updatedKyCongBo);

        restKyCongBoMockMvc.perform(put("/api/ky-cong-bos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kyCongBoDTO)))
            .andExpect(status().isOk());

        // Validate the KyCongBo in the database
        List<KyCongBo> kyCongBoList = kyCongBoRepository.findAll();
        assertThat(kyCongBoList).hasSize(databaseSizeBeforeUpdate);
        KyCongBo testKyCongBo = kyCongBoList.get(kyCongBoList.size() - 1);
        assertThat(testKyCongBo.getKyCongBoCode()).isEqualTo(UPDATED_KY_CONG_BO_CODE);
        assertThat(testKyCongBo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testKyCongBo.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingKyCongBo() throws Exception {
        int databaseSizeBeforeUpdate = kyCongBoRepository.findAll().size();

        // Create the KyCongBo
        KyCongBoDTO kyCongBoDTO = kyCongBoMapper.toDto(kyCongBo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKyCongBoMockMvc.perform(put("/api/ky-cong-bos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kyCongBoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KyCongBo in the database
        List<KyCongBo> kyCongBoList = kyCongBoRepository.findAll();
        assertThat(kyCongBoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKyCongBo() throws Exception {
        // Initialize the database
        kyCongBoRepository.saveAndFlush(kyCongBo);

        int databaseSizeBeforeDelete = kyCongBoRepository.findAll().size();

        // Delete the kyCongBo
        restKyCongBoMockMvc.perform(delete("/api/ky-cong-bos/{id}", kyCongBo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<KyCongBo> kyCongBoList = kyCongBoRepository.findAll();
        assertThat(kyCongBoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KyCongBo.class);
        KyCongBo kyCongBo1 = new KyCongBo();
        kyCongBo1.setId(1L);
        KyCongBo kyCongBo2 = new KyCongBo();
        kyCongBo2.setId(kyCongBo1.getId());
        assertThat(kyCongBo1).isEqualTo(kyCongBo2);
        kyCongBo2.setId(2L);
        assertThat(kyCongBo1).isNotEqualTo(kyCongBo2);
        kyCongBo1.setId(null);
        assertThat(kyCongBo1).isNotEqualTo(kyCongBo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KyCongBoDTO.class);
        KyCongBoDTO kyCongBoDTO1 = new KyCongBoDTO();
        kyCongBoDTO1.setId(1L);
        KyCongBoDTO kyCongBoDTO2 = new KyCongBoDTO();
        assertThat(kyCongBoDTO1).isNotEqualTo(kyCongBoDTO2);
        kyCongBoDTO2.setId(kyCongBoDTO1.getId());
        assertThat(kyCongBoDTO1).isEqualTo(kyCongBoDTO2);
        kyCongBoDTO2.setId(2L);
        assertThat(kyCongBoDTO1).isNotEqualTo(kyCongBoDTO2);
        kyCongBoDTO1.setId(null);
        assertThat(kyCongBoDTO1).isNotEqualTo(kyCongBoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(kyCongBoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(kyCongBoMapper.fromId(null)).isNull();
    }
}
