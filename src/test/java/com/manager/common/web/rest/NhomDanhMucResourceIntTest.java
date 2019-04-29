package com.manager.common.web.rest;

import com.manager.common.CommonApp;
import com.manager.common.domain.NhomDanhMuc;
import com.manager.common.domain.enumeration.Status;
import com.manager.common.repository.NhomDanhMucRepository;
import com.manager.common.service.NhomDanhMucService;
import com.manager.common.service.dto.NhomDanhMucDTO;
import com.manager.common.service.mapper.NhomDanhMucMapper;
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
 * Test class for the NhomDanhMucResource REST controller.
 *
 * @see NhomDanhMucResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class NhomDanhMucResourceIntTest {

    private static final String DEFAULT_NHOM_DANH_MUC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_NHOM_DANH_MUC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.PUBLISH;
    private static final Status UPDATED_STATUS = Status.UNPUBLISH;

    @Autowired
    private NhomDanhMucRepository nhomDanhMucRepository;

    @Autowired
    private NhomDanhMucMapper nhomDanhMucMapper;

    @Autowired
    private NhomDanhMucService nhomDanhMucService;

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

    private MockMvc restNhomDanhMucMockMvc;

    private NhomDanhMuc nhomDanhMuc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NhomDanhMucResource nhomDanhMucResource = new NhomDanhMucResource(nhomDanhMucService);
        this.restNhomDanhMucMockMvc = MockMvcBuilders.standaloneSetup(nhomDanhMucResource)
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
    public static NhomDanhMuc createEntity(EntityManager em) {
        NhomDanhMuc nhomDanhMuc = new NhomDanhMuc()
            .nhomDanhMucCode(DEFAULT_NHOM_DANH_MUC_CODE)
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS);
        return nhomDanhMuc;
    }

    @Before
    public void initTest() {
        nhomDanhMuc = createEntity(em);
    }

    @Test
    @Transactional
    public void createNhomDanhMuc() throws Exception {
        int databaseSizeBeforeCreate = nhomDanhMucRepository.findAll().size();

        // Create the NhomDanhMuc
        NhomDanhMucDTO nhomDanhMucDTO = nhomDanhMucMapper.toDto(nhomDanhMuc);
        restNhomDanhMucMockMvc.perform(post("/api/nhom-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomDanhMucDTO)))
            .andExpect(status().isCreated());

        // Validate the NhomDanhMuc in the database
        List<NhomDanhMuc> nhomDanhMucList = nhomDanhMucRepository.findAll();
        assertThat(nhomDanhMucList).hasSize(databaseSizeBeforeCreate + 1);
        NhomDanhMuc testNhomDanhMuc = nhomDanhMucList.get(nhomDanhMucList.size() - 1);
        assertThat(testNhomDanhMuc.getNhomDanhMucCode()).isEqualTo(DEFAULT_NHOM_DANH_MUC_CODE);
        assertThat(testNhomDanhMuc.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNhomDanhMuc.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createNhomDanhMucWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nhomDanhMucRepository.findAll().size();

        // Create the NhomDanhMuc with an existing ID
        nhomDanhMuc.setId(1L);
        NhomDanhMucDTO nhomDanhMucDTO = nhomDanhMucMapper.toDto(nhomDanhMuc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhomDanhMucMockMvc.perform(post("/api/nhom-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomDanhMucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhomDanhMuc in the database
        List<NhomDanhMuc> nhomDanhMucList = nhomDanhMucRepository.findAll();
        assertThat(nhomDanhMucList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNhomDanhMucCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhomDanhMucRepository.findAll().size();
        // set the field null
        nhomDanhMuc.setNhomDanhMucCode(null);

        // Create the NhomDanhMuc, which fails.
        NhomDanhMucDTO nhomDanhMucDTO = nhomDanhMucMapper.toDto(nhomDanhMuc);

        restNhomDanhMucMockMvc.perform(post("/api/nhom-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomDanhMucDTO)))
            .andExpect(status().isBadRequest());

        List<NhomDanhMuc> nhomDanhMucList = nhomDanhMucRepository.findAll();
        assertThat(nhomDanhMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhomDanhMucRepository.findAll().size();
        // set the field null
        nhomDanhMuc.setName(null);

        // Create the NhomDanhMuc, which fails.
        NhomDanhMucDTO nhomDanhMucDTO = nhomDanhMucMapper.toDto(nhomDanhMuc);

        restNhomDanhMucMockMvc.perform(post("/api/nhom-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomDanhMucDTO)))
            .andExpect(status().isBadRequest());

        List<NhomDanhMuc> nhomDanhMucList = nhomDanhMucRepository.findAll();
        assertThat(nhomDanhMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhomDanhMucRepository.findAll().size();
        // set the field null
        nhomDanhMuc.setStatus(null);

        // Create the NhomDanhMuc, which fails.
        NhomDanhMucDTO nhomDanhMucDTO = nhomDanhMucMapper.toDto(nhomDanhMuc);

        restNhomDanhMucMockMvc.perform(post("/api/nhom-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomDanhMucDTO)))
            .andExpect(status().isBadRequest());

        List<NhomDanhMuc> nhomDanhMucList = nhomDanhMucRepository.findAll();
        assertThat(nhomDanhMucList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNhomDanhMucs() throws Exception {
        // Initialize the database
        nhomDanhMucRepository.saveAndFlush(nhomDanhMuc);

        // Get all the nhomDanhMucList
        restNhomDanhMucMockMvc.perform(get("/api/nhom-danh-mucs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhomDanhMuc.getId().intValue())))
            .andExpect(jsonPath("$.[*].nhomDanhMucCode").value(hasItem(DEFAULT_NHOM_DANH_MUC_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getNhomDanhMuc() throws Exception {
        // Initialize the database
        nhomDanhMucRepository.saveAndFlush(nhomDanhMuc);

        // Get the nhomDanhMuc
        restNhomDanhMucMockMvc.perform(get("/api/nhom-danh-mucs/{id}", nhomDanhMuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nhomDanhMuc.getId().intValue()))
            .andExpect(jsonPath("$.nhomDanhMucCode").value(DEFAULT_NHOM_DANH_MUC_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNhomDanhMuc() throws Exception {
        // Get the nhomDanhMuc
        restNhomDanhMucMockMvc.perform(get("/api/nhom-danh-mucs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNhomDanhMuc() throws Exception {
        // Initialize the database
        nhomDanhMucRepository.saveAndFlush(nhomDanhMuc);

        int databaseSizeBeforeUpdate = nhomDanhMucRepository.findAll().size();

        // Update the nhomDanhMuc
        NhomDanhMuc updatedNhomDanhMuc = nhomDanhMucRepository.findById(nhomDanhMuc.getId()).get();
        // Disconnect from session so that the updates on updatedNhomDanhMuc are not directly saved in db
        em.detach(updatedNhomDanhMuc);
        updatedNhomDanhMuc
            .nhomDanhMucCode(UPDATED_NHOM_DANH_MUC_CODE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        NhomDanhMucDTO nhomDanhMucDTO = nhomDanhMucMapper.toDto(updatedNhomDanhMuc);

        restNhomDanhMucMockMvc.perform(put("/api/nhom-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomDanhMucDTO)))
            .andExpect(status().isOk());

        // Validate the NhomDanhMuc in the database
        List<NhomDanhMuc> nhomDanhMucList = nhomDanhMucRepository.findAll();
        assertThat(nhomDanhMucList).hasSize(databaseSizeBeforeUpdate);
        NhomDanhMuc testNhomDanhMuc = nhomDanhMucList.get(nhomDanhMucList.size() - 1);
        assertThat(testNhomDanhMuc.getNhomDanhMucCode()).isEqualTo(UPDATED_NHOM_DANH_MUC_CODE);
        assertThat(testNhomDanhMuc.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNhomDanhMuc.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingNhomDanhMuc() throws Exception {
        int databaseSizeBeforeUpdate = nhomDanhMucRepository.findAll().size();

        // Create the NhomDanhMuc
        NhomDanhMucDTO nhomDanhMucDTO = nhomDanhMucMapper.toDto(nhomDanhMuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhomDanhMucMockMvc.perform(put("/api/nhom-danh-mucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomDanhMucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhomDanhMuc in the database
        List<NhomDanhMuc> nhomDanhMucList = nhomDanhMucRepository.findAll();
        assertThat(nhomDanhMucList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNhomDanhMuc() throws Exception {
        // Initialize the database
        nhomDanhMucRepository.saveAndFlush(nhomDanhMuc);

        int databaseSizeBeforeDelete = nhomDanhMucRepository.findAll().size();

        // Delete the nhomDanhMuc
        restNhomDanhMucMockMvc.perform(delete("/api/nhom-danh-mucs/{id}", nhomDanhMuc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NhomDanhMuc> nhomDanhMucList = nhomDanhMucRepository.findAll();
        assertThat(nhomDanhMucList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhomDanhMuc.class);
        NhomDanhMuc nhomDanhMuc1 = new NhomDanhMuc();
        nhomDanhMuc1.setId(1L);
        NhomDanhMuc nhomDanhMuc2 = new NhomDanhMuc();
        nhomDanhMuc2.setId(nhomDanhMuc1.getId());
        assertThat(nhomDanhMuc1).isEqualTo(nhomDanhMuc2);
        nhomDanhMuc2.setId(2L);
        assertThat(nhomDanhMuc1).isNotEqualTo(nhomDanhMuc2);
        nhomDanhMuc1.setId(null);
        assertThat(nhomDanhMuc1).isNotEqualTo(nhomDanhMuc2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhomDanhMucDTO.class);
        NhomDanhMucDTO nhomDanhMucDTO1 = new NhomDanhMucDTO();
        nhomDanhMucDTO1.setId(1L);
        NhomDanhMucDTO nhomDanhMucDTO2 = new NhomDanhMucDTO();
        assertThat(nhomDanhMucDTO1).isNotEqualTo(nhomDanhMucDTO2);
        nhomDanhMucDTO2.setId(nhomDanhMucDTO1.getId());
        assertThat(nhomDanhMucDTO1).isEqualTo(nhomDanhMucDTO2);
        nhomDanhMucDTO2.setId(2L);
        assertThat(nhomDanhMucDTO1).isNotEqualTo(nhomDanhMucDTO2);
        nhomDanhMucDTO1.setId(null);
        assertThat(nhomDanhMucDTO1).isNotEqualTo(nhomDanhMucDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nhomDanhMucMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nhomDanhMucMapper.fromId(null)).isNull();
    }
}
