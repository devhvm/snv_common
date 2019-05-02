package com.manager.common.web.rest;

import com.manager.common.CommonApp;
import com.manager.common.domain.MaDinhDanhDonVi;
import com.manager.common.repository.MaDinhDanhDonViRepository;
import com.manager.common.service.MaDinhDanhDonViService;
import com.manager.common.service.dto.MaDinhDanhDonViDTO;
import com.manager.common.service.mapper.MaDinhDanhDonViMapper;
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
 * Test class for the MaDinhDanhDonViResource REST controller.
 *
 * @see MaDinhDanhDonViResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class MaDinhDanhDonViResourceIntTest {

    private static final String DEFAULT_MA_DINH_DANH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MA_DINH_DANH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_LEVEL = "BBBBBBBBBB";

    @Autowired
    private MaDinhDanhDonViRepository maDinhDanhDonViRepository;

    @Autowired
    private MaDinhDanhDonViMapper maDinhDanhDonViMapper;

    @Autowired
    private MaDinhDanhDonViService maDinhDanhDonViService;

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

    private MockMvc restMaDinhDanhDonViMockMvc;

    private MaDinhDanhDonVi maDinhDanhDonVi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MaDinhDanhDonViResource maDinhDanhDonViResource = new MaDinhDanhDonViResource(maDinhDanhDonViService);
        this.restMaDinhDanhDonViMockMvc = MockMvcBuilders.standaloneSetup(maDinhDanhDonViResource)
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
    public static MaDinhDanhDonVi createEntity(EntityManager em) {
        MaDinhDanhDonVi maDinhDanhDonVi = new MaDinhDanhDonVi()
            .maDinhDanhCode(DEFAULT_MA_DINH_DANH_CODE)
            .parentCode(DEFAULT_PARENT_CODE)
            .name(DEFAULT_NAME)
            .level(DEFAULT_LEVEL);
        return maDinhDanhDonVi;
    }

    @Before
    public void initTest() {
        maDinhDanhDonVi = createEntity(em);
    }

    @Test
    @Transactional
    public void createMaDinhDanhDonVi() throws Exception {
        int databaseSizeBeforeCreate = maDinhDanhDonViRepository.findAll().size();

        // Create the MaDinhDanhDonVi
        MaDinhDanhDonViDTO maDinhDanhDonViDTO = maDinhDanhDonViMapper.toDto(maDinhDanhDonVi);
        restMaDinhDanhDonViMockMvc.perform(post("/api/ma-dinh-danh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maDinhDanhDonViDTO)))
            .andExpect(status().isCreated());

        // Validate the MaDinhDanhDonVi in the database
        List<MaDinhDanhDonVi> maDinhDanhDonViList = maDinhDanhDonViRepository.findAll();
        assertThat(maDinhDanhDonViList).hasSize(databaseSizeBeforeCreate + 1);
        MaDinhDanhDonVi testMaDinhDanhDonVi = maDinhDanhDonViList.get(maDinhDanhDonViList.size() - 1);
        assertThat(testMaDinhDanhDonVi.getMaDinhDanhCode()).isEqualTo(DEFAULT_MA_DINH_DANH_CODE);
        assertThat(testMaDinhDanhDonVi.getParentCode()).isEqualTo(DEFAULT_PARENT_CODE);
        assertThat(testMaDinhDanhDonVi.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMaDinhDanhDonVi.getLevel()).isEqualTo(DEFAULT_LEVEL);
    }

    @Test
    @Transactional
    public void createMaDinhDanhDonViWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = maDinhDanhDonViRepository.findAll().size();

        // Create the MaDinhDanhDonVi with an existing ID
        maDinhDanhDonVi.setId(1L);
        MaDinhDanhDonViDTO maDinhDanhDonViDTO = maDinhDanhDonViMapper.toDto(maDinhDanhDonVi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaDinhDanhDonViMockMvc.perform(post("/api/ma-dinh-danh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maDinhDanhDonViDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MaDinhDanhDonVi in the database
        List<MaDinhDanhDonVi> maDinhDanhDonViList = maDinhDanhDonViRepository.findAll();
        assertThat(maDinhDanhDonViList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMaDinhDanhCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = maDinhDanhDonViRepository.findAll().size();
        // set the field null
        maDinhDanhDonVi.setMaDinhDanhCode(null);

        // Create the MaDinhDanhDonVi, which fails.
        MaDinhDanhDonViDTO maDinhDanhDonViDTO = maDinhDanhDonViMapper.toDto(maDinhDanhDonVi);

        restMaDinhDanhDonViMockMvc.perform(post("/api/ma-dinh-danh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maDinhDanhDonViDTO)))
            .andExpect(status().isBadRequest());

        List<MaDinhDanhDonVi> maDinhDanhDonViList = maDinhDanhDonViRepository.findAll();
        assertThat(maDinhDanhDonViList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = maDinhDanhDonViRepository.findAll().size();
        // set the field null
        maDinhDanhDonVi.setName(null);

        // Create the MaDinhDanhDonVi, which fails.
        MaDinhDanhDonViDTO maDinhDanhDonViDTO = maDinhDanhDonViMapper.toDto(maDinhDanhDonVi);

        restMaDinhDanhDonViMockMvc.perform(post("/api/ma-dinh-danh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maDinhDanhDonViDTO)))
            .andExpect(status().isBadRequest());

        List<MaDinhDanhDonVi> maDinhDanhDonViList = maDinhDanhDonViRepository.findAll();
        assertThat(maDinhDanhDonViList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = maDinhDanhDonViRepository.findAll().size();
        // set the field null
        maDinhDanhDonVi.setLevel(null);

        // Create the MaDinhDanhDonVi, which fails.
        MaDinhDanhDonViDTO maDinhDanhDonViDTO = maDinhDanhDonViMapper.toDto(maDinhDanhDonVi);

        restMaDinhDanhDonViMockMvc.perform(post("/api/ma-dinh-danh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maDinhDanhDonViDTO)))
            .andExpect(status().isBadRequest());

        List<MaDinhDanhDonVi> maDinhDanhDonViList = maDinhDanhDonViRepository.findAll();
        assertThat(maDinhDanhDonViList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMaDinhDanhDonVis() throws Exception {
        // Initialize the database
        maDinhDanhDonViRepository.saveAndFlush(maDinhDanhDonVi);

        // Get all the maDinhDanhDonViList
        restMaDinhDanhDonViMockMvc.perform(get("/api/ma-dinh-danh-don-vis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(maDinhDanhDonVi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDinhDanhCode").value(hasItem(DEFAULT_MA_DINH_DANH_CODE.toString())))
            .andExpect(jsonPath("$.[*].parentCode").value(hasItem(DEFAULT_PARENT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL.toString())));
    }

    @Test
    @Transactional
    public void getMaDinhDanhDonVi() throws Exception {
        // Initialize the database
        maDinhDanhDonViRepository.saveAndFlush(maDinhDanhDonVi);

        // Get the maDinhDanhDonVi
        restMaDinhDanhDonViMockMvc.perform(get("/api/ma-dinh-danh-don-vis/{id}", maDinhDanhDonVi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(maDinhDanhDonVi.getId().intValue()))
            .andExpect(jsonPath("$.maDinhDanhCode").value(DEFAULT_MA_DINH_DANH_CODE.toString()))
            .andExpect(jsonPath("$.parentCode").value(DEFAULT_PARENT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMaDinhDanhDonVi() throws Exception {
        // Get the maDinhDanhDonVi
        restMaDinhDanhDonViMockMvc.perform(get("/api/ma-dinh-danh-don-vis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaDinhDanhDonVi() throws Exception {
        // Initialize the database
        maDinhDanhDonViRepository.saveAndFlush(maDinhDanhDonVi);

        int databaseSizeBeforeUpdate = maDinhDanhDonViRepository.findAll().size();

        // Update the maDinhDanhDonVi
        MaDinhDanhDonVi updatedMaDinhDanhDonVi = maDinhDanhDonViRepository.findById(maDinhDanhDonVi.getId()).get();
        // Disconnect from session so that the updates on updatedMaDinhDanhDonVi are not directly saved in db
        em.detach(updatedMaDinhDanhDonVi);
        updatedMaDinhDanhDonVi
            .maDinhDanhCode(UPDATED_MA_DINH_DANH_CODE)
            .parentCode(UPDATED_PARENT_CODE)
            .name(UPDATED_NAME)
            .level(UPDATED_LEVEL);
        MaDinhDanhDonViDTO maDinhDanhDonViDTO = maDinhDanhDonViMapper.toDto(updatedMaDinhDanhDonVi);

        restMaDinhDanhDonViMockMvc.perform(put("/api/ma-dinh-danh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maDinhDanhDonViDTO)))
            .andExpect(status().isOk());

        // Validate the MaDinhDanhDonVi in the database
        List<MaDinhDanhDonVi> maDinhDanhDonViList = maDinhDanhDonViRepository.findAll();
        assertThat(maDinhDanhDonViList).hasSize(databaseSizeBeforeUpdate);
        MaDinhDanhDonVi testMaDinhDanhDonVi = maDinhDanhDonViList.get(maDinhDanhDonViList.size() - 1);
        assertThat(testMaDinhDanhDonVi.getMaDinhDanhCode()).isEqualTo(UPDATED_MA_DINH_DANH_CODE);
        assertThat(testMaDinhDanhDonVi.getParentCode()).isEqualTo(UPDATED_PARENT_CODE);
        assertThat(testMaDinhDanhDonVi.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMaDinhDanhDonVi.getLevel()).isEqualTo(UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingMaDinhDanhDonVi() throws Exception {
        int databaseSizeBeforeUpdate = maDinhDanhDonViRepository.findAll().size();

        // Create the MaDinhDanhDonVi
        MaDinhDanhDonViDTO maDinhDanhDonViDTO = maDinhDanhDonViMapper.toDto(maDinhDanhDonVi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMaDinhDanhDonViMockMvc.perform(put("/api/ma-dinh-danh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maDinhDanhDonViDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MaDinhDanhDonVi in the database
        List<MaDinhDanhDonVi> maDinhDanhDonViList = maDinhDanhDonViRepository.findAll();
        assertThat(maDinhDanhDonViList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMaDinhDanhDonVi() throws Exception {
        // Initialize the database
        maDinhDanhDonViRepository.saveAndFlush(maDinhDanhDonVi);

        int databaseSizeBeforeDelete = maDinhDanhDonViRepository.findAll().size();

        // Delete the maDinhDanhDonVi
        restMaDinhDanhDonViMockMvc.perform(delete("/api/ma-dinh-danh-don-vis/{id}", maDinhDanhDonVi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MaDinhDanhDonVi> maDinhDanhDonViList = maDinhDanhDonViRepository.findAll();
        assertThat(maDinhDanhDonViList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MaDinhDanhDonVi.class);
        MaDinhDanhDonVi maDinhDanhDonVi1 = new MaDinhDanhDonVi();
        maDinhDanhDonVi1.setId(1L);
        MaDinhDanhDonVi maDinhDanhDonVi2 = new MaDinhDanhDonVi();
        maDinhDanhDonVi2.setId(maDinhDanhDonVi1.getId());
        assertThat(maDinhDanhDonVi1).isEqualTo(maDinhDanhDonVi2);
        maDinhDanhDonVi2.setId(2L);
        assertThat(maDinhDanhDonVi1).isNotEqualTo(maDinhDanhDonVi2);
        maDinhDanhDonVi1.setId(null);
        assertThat(maDinhDanhDonVi1).isNotEqualTo(maDinhDanhDonVi2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MaDinhDanhDonViDTO.class);
        MaDinhDanhDonViDTO maDinhDanhDonViDTO1 = new MaDinhDanhDonViDTO();
        maDinhDanhDonViDTO1.setId(1L);
        MaDinhDanhDonViDTO maDinhDanhDonViDTO2 = new MaDinhDanhDonViDTO();
        assertThat(maDinhDanhDonViDTO1).isNotEqualTo(maDinhDanhDonViDTO2);
        maDinhDanhDonViDTO2.setId(maDinhDanhDonViDTO1.getId());
        assertThat(maDinhDanhDonViDTO1).isEqualTo(maDinhDanhDonViDTO2);
        maDinhDanhDonViDTO2.setId(2L);
        assertThat(maDinhDanhDonViDTO1).isNotEqualTo(maDinhDanhDonViDTO2);
        maDinhDanhDonViDTO1.setId(null);
        assertThat(maDinhDanhDonViDTO1).isNotEqualTo(maDinhDanhDonViDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(maDinhDanhDonViMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(maDinhDanhDonViMapper.fromId(null)).isNull();
    }
}
