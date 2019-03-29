package com.manager.common.web.rest;

import com.manager.common.CommonApp;

import com.manager.common.domain.NhomPhanLoai;
import com.manager.common.repository.NhomPhanLoaiRepository;
import com.manager.common.service.NhomPhanLoaiService;
import com.manager.common.service.dto.NhomPhanLoaiDTO;
import com.manager.common.service.mapper.NhomPhanLoaiMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static com.manager.common.web.rest.TestUtil.sameInstant;
import static com.manager.common.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.manager.common.domain.enumeration.Status;
/**
 * Test class for the NhomPhanLoaiResource REST controller.
 *
 * @see NhomPhanLoaiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class NhomPhanLoaiResourceIntTest {

    private static final String DEFAULT_NHOM_PHAN_LOAI_CODE = "AAAAAAAAAA";
    private static final String UPDATED_NHOM_PHAN_LOAI_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Status DEFAULT_STATUS = Status.PUBLISH;
    private static final Status UPDATED_STATUS = Status.UNPUBLISH;

    private static final String DEFAULT_PROGRAM = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAM = "BBBBBBBBBB";

    @Autowired
    private NhomPhanLoaiRepository nhomPhanLoaiRepository;

    @Autowired
    private NhomPhanLoaiMapper nhomPhanLoaiMapper;

    @Autowired
    private NhomPhanLoaiService nhomPhanLoaiService;

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

    private MockMvc restNhomPhanLoaiMockMvc;

    private NhomPhanLoai nhomPhanLoai;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NhomPhanLoaiResource nhomPhanLoaiResource = new NhomPhanLoaiResource(nhomPhanLoaiService);
        this.restNhomPhanLoaiMockMvc = MockMvcBuilders.standaloneSetup(nhomPhanLoaiResource)
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
    public static NhomPhanLoai createEntity(EntityManager em) {
        NhomPhanLoai nhomPhanLoai = new NhomPhanLoai()
            .nhomPhanLoaiCode(DEFAULT_NHOM_PHAN_LOAI_CODE)
            .name(DEFAULT_NAME)
            .userName(DEFAULT_USER_NAME)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME)
            .status(DEFAULT_STATUS)
            .program(DEFAULT_PROGRAM);
        return nhomPhanLoai;
    }

    @Before
    public void initTest() {
        nhomPhanLoai = createEntity(em);
    }

    @Test
    @Transactional
    public void createNhomPhanLoai() throws Exception {
        int databaseSizeBeforeCreate = nhomPhanLoaiRepository.findAll().size();

        // Create the NhomPhanLoai
        NhomPhanLoaiDTO nhomPhanLoaiDTO = nhomPhanLoaiMapper.toDto(nhomPhanLoai);
        restNhomPhanLoaiMockMvc.perform(post("/api/nhom-phan-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomPhanLoaiDTO)))
            .andExpect(status().isCreated());

        // Validate the NhomPhanLoai in the database
        List<NhomPhanLoai> nhomPhanLoaiList = nhomPhanLoaiRepository.findAll();
        assertThat(nhomPhanLoaiList).hasSize(databaseSizeBeforeCreate + 1);
        NhomPhanLoai testNhomPhanLoai = nhomPhanLoaiList.get(nhomPhanLoaiList.size() - 1);
        assertThat(testNhomPhanLoai.getNhomPhanLoaiCode()).isEqualTo(DEFAULT_NHOM_PHAN_LOAI_CODE);
        assertThat(testNhomPhanLoai.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNhomPhanLoai.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testNhomPhanLoai.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testNhomPhanLoai.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testNhomPhanLoai.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNhomPhanLoai.getProgram()).isEqualTo(DEFAULT_PROGRAM);
    }

    @Test
    @Transactional
    public void createNhomPhanLoaiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nhomPhanLoaiRepository.findAll().size();

        // Create the NhomPhanLoai with an existing ID
        nhomPhanLoai.setId(1L);
        NhomPhanLoaiDTO nhomPhanLoaiDTO = nhomPhanLoaiMapper.toDto(nhomPhanLoai);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhomPhanLoaiMockMvc.perform(post("/api/nhom-phan-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomPhanLoaiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhomPhanLoai in the database
        List<NhomPhanLoai> nhomPhanLoaiList = nhomPhanLoaiRepository.findAll();
        assertThat(nhomPhanLoaiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNhomPhanLoaiCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhomPhanLoaiRepository.findAll().size();
        // set the field null
        nhomPhanLoai.setNhomPhanLoaiCode(null);

        // Create the NhomPhanLoai, which fails.
        NhomPhanLoaiDTO nhomPhanLoaiDTO = nhomPhanLoaiMapper.toDto(nhomPhanLoai);

        restNhomPhanLoaiMockMvc.perform(post("/api/nhom-phan-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomPhanLoaiDTO)))
            .andExpect(status().isBadRequest());

        List<NhomPhanLoai> nhomPhanLoaiList = nhomPhanLoaiRepository.findAll();
        assertThat(nhomPhanLoaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhomPhanLoaiRepository.findAll().size();
        // set the field null
        nhomPhanLoai.setName(null);

        // Create the NhomPhanLoai, which fails.
        NhomPhanLoaiDTO nhomPhanLoaiDTO = nhomPhanLoaiMapper.toDto(nhomPhanLoai);

        restNhomPhanLoaiMockMvc.perform(post("/api/nhom-phan-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomPhanLoaiDTO)))
            .andExpect(status().isBadRequest());

        List<NhomPhanLoai> nhomPhanLoaiList = nhomPhanLoaiRepository.findAll();
        assertThat(nhomPhanLoaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhomPhanLoaiRepository.findAll().size();
        // set the field null
        nhomPhanLoai.setUserName(null);

        // Create the NhomPhanLoai, which fails.
        NhomPhanLoaiDTO nhomPhanLoaiDTO = nhomPhanLoaiMapper.toDto(nhomPhanLoai);

        restNhomPhanLoaiMockMvc.perform(post("/api/nhom-phan-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomPhanLoaiDTO)))
            .andExpect(status().isBadRequest());

        List<NhomPhanLoai> nhomPhanLoaiList = nhomPhanLoaiRepository.findAll();
        assertThat(nhomPhanLoaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhomPhanLoaiRepository.findAll().size();
        // set the field null
        nhomPhanLoai.setCreateTime(null);

        // Create the NhomPhanLoai, which fails.
        NhomPhanLoaiDTO nhomPhanLoaiDTO = nhomPhanLoaiMapper.toDto(nhomPhanLoai);

        restNhomPhanLoaiMockMvc.perform(post("/api/nhom-phan-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomPhanLoaiDTO)))
            .andExpect(status().isBadRequest());

        List<NhomPhanLoai> nhomPhanLoaiList = nhomPhanLoaiRepository.findAll();
        assertThat(nhomPhanLoaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhomPhanLoaiRepository.findAll().size();
        // set the field null
        nhomPhanLoai.setUpdateTime(null);

        // Create the NhomPhanLoai, which fails.
        NhomPhanLoaiDTO nhomPhanLoaiDTO = nhomPhanLoaiMapper.toDto(nhomPhanLoai);

        restNhomPhanLoaiMockMvc.perform(post("/api/nhom-phan-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomPhanLoaiDTO)))
            .andExpect(status().isBadRequest());

        List<NhomPhanLoai> nhomPhanLoaiList = nhomPhanLoaiRepository.findAll();
        assertThat(nhomPhanLoaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhomPhanLoaiRepository.findAll().size();
        // set the field null
        nhomPhanLoai.setStatus(null);

        // Create the NhomPhanLoai, which fails.
        NhomPhanLoaiDTO nhomPhanLoaiDTO = nhomPhanLoaiMapper.toDto(nhomPhanLoai);

        restNhomPhanLoaiMockMvc.perform(post("/api/nhom-phan-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomPhanLoaiDTO)))
            .andExpect(status().isBadRequest());

        List<NhomPhanLoai> nhomPhanLoaiList = nhomPhanLoaiRepository.findAll();
        assertThat(nhomPhanLoaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProgramIsRequired() throws Exception {
        int databaseSizeBeforeTest = nhomPhanLoaiRepository.findAll().size();
        // set the field null
        nhomPhanLoai.setProgram(null);

        // Create the NhomPhanLoai, which fails.
        NhomPhanLoaiDTO nhomPhanLoaiDTO = nhomPhanLoaiMapper.toDto(nhomPhanLoai);

        restNhomPhanLoaiMockMvc.perform(post("/api/nhom-phan-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomPhanLoaiDTO)))
            .andExpect(status().isBadRequest());

        List<NhomPhanLoai> nhomPhanLoaiList = nhomPhanLoaiRepository.findAll();
        assertThat(nhomPhanLoaiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNhomPhanLoais() throws Exception {
        // Initialize the database
        nhomPhanLoaiRepository.saveAndFlush(nhomPhanLoai);

        // Get all the nhomPhanLoaiList
        restNhomPhanLoaiMockMvc.perform(get("/api/nhom-phan-loais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nhomPhanLoai.getId().intValue())))
            .andExpect(jsonPath("$.[*].nhomPhanLoaiCode").value(hasItem(DEFAULT_NHOM_PHAN_LOAI_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].program").value(hasItem(DEFAULT_PROGRAM.toString())));
    }
    
    @Test
    @Transactional
    public void getNhomPhanLoai() throws Exception {
        // Initialize the database
        nhomPhanLoaiRepository.saveAndFlush(nhomPhanLoai);

        // Get the nhomPhanLoai
        restNhomPhanLoaiMockMvc.perform(get("/api/nhom-phan-loais/{id}", nhomPhanLoai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nhomPhanLoai.getId().intValue()))
            .andExpect(jsonPath("$.nhomPhanLoaiCode").value(DEFAULT_NHOM_PHAN_LOAI_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.program").value(DEFAULT_PROGRAM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNhomPhanLoai() throws Exception {
        // Get the nhomPhanLoai
        restNhomPhanLoaiMockMvc.perform(get("/api/nhom-phan-loais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNhomPhanLoai() throws Exception {
        // Initialize the database
        nhomPhanLoaiRepository.saveAndFlush(nhomPhanLoai);

        int databaseSizeBeforeUpdate = nhomPhanLoaiRepository.findAll().size();

        // Update the nhomPhanLoai
        NhomPhanLoai updatedNhomPhanLoai = nhomPhanLoaiRepository.findById(nhomPhanLoai.getId()).get();
        // Disconnect from session so that the updates on updatedNhomPhanLoai are not directly saved in db
        em.detach(updatedNhomPhanLoai);
        updatedNhomPhanLoai
            .nhomPhanLoaiCode(UPDATED_NHOM_PHAN_LOAI_CODE)
            .name(UPDATED_NAME)
            .userName(UPDATED_USER_NAME)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME)
            .status(UPDATED_STATUS)
            .program(UPDATED_PROGRAM);
        NhomPhanLoaiDTO nhomPhanLoaiDTO = nhomPhanLoaiMapper.toDto(updatedNhomPhanLoai);

        restNhomPhanLoaiMockMvc.perform(put("/api/nhom-phan-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomPhanLoaiDTO)))
            .andExpect(status().isOk());

        // Validate the NhomPhanLoai in the database
        List<NhomPhanLoai> nhomPhanLoaiList = nhomPhanLoaiRepository.findAll();
        assertThat(nhomPhanLoaiList).hasSize(databaseSizeBeforeUpdate);
        NhomPhanLoai testNhomPhanLoai = nhomPhanLoaiList.get(nhomPhanLoaiList.size() - 1);
        assertThat(testNhomPhanLoai.getNhomPhanLoaiCode()).isEqualTo(UPDATED_NHOM_PHAN_LOAI_CODE);
        assertThat(testNhomPhanLoai.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNhomPhanLoai.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testNhomPhanLoai.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testNhomPhanLoai.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testNhomPhanLoai.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNhomPhanLoai.getProgram()).isEqualTo(UPDATED_PROGRAM);
    }

    @Test
    @Transactional
    public void updateNonExistingNhomPhanLoai() throws Exception {
        int databaseSizeBeforeUpdate = nhomPhanLoaiRepository.findAll().size();

        // Create the NhomPhanLoai
        NhomPhanLoaiDTO nhomPhanLoaiDTO = nhomPhanLoaiMapper.toDto(nhomPhanLoai);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhomPhanLoaiMockMvc.perform(put("/api/nhom-phan-loais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nhomPhanLoaiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhomPhanLoai in the database
        List<NhomPhanLoai> nhomPhanLoaiList = nhomPhanLoaiRepository.findAll();
        assertThat(nhomPhanLoaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNhomPhanLoai() throws Exception {
        // Initialize the database
        nhomPhanLoaiRepository.saveAndFlush(nhomPhanLoai);

        int databaseSizeBeforeDelete = nhomPhanLoaiRepository.findAll().size();

        // Delete the nhomPhanLoai
        restNhomPhanLoaiMockMvc.perform(delete("/api/nhom-phan-loais/{id}", nhomPhanLoai.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NhomPhanLoai> nhomPhanLoaiList = nhomPhanLoaiRepository.findAll();
        assertThat(nhomPhanLoaiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhomPhanLoai.class);
        NhomPhanLoai nhomPhanLoai1 = new NhomPhanLoai();
        nhomPhanLoai1.setId(1L);
        NhomPhanLoai nhomPhanLoai2 = new NhomPhanLoai();
        nhomPhanLoai2.setId(nhomPhanLoai1.getId());
        assertThat(nhomPhanLoai1).isEqualTo(nhomPhanLoai2);
        nhomPhanLoai2.setId(2L);
        assertThat(nhomPhanLoai1).isNotEqualTo(nhomPhanLoai2);
        nhomPhanLoai1.setId(null);
        assertThat(nhomPhanLoai1).isNotEqualTo(nhomPhanLoai2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhomPhanLoaiDTO.class);
        NhomPhanLoaiDTO nhomPhanLoaiDTO1 = new NhomPhanLoaiDTO();
        nhomPhanLoaiDTO1.setId(1L);
        NhomPhanLoaiDTO nhomPhanLoaiDTO2 = new NhomPhanLoaiDTO();
        assertThat(nhomPhanLoaiDTO1).isNotEqualTo(nhomPhanLoaiDTO2);
        nhomPhanLoaiDTO2.setId(nhomPhanLoaiDTO1.getId());
        assertThat(nhomPhanLoaiDTO1).isEqualTo(nhomPhanLoaiDTO2);
        nhomPhanLoaiDTO2.setId(2L);
        assertThat(nhomPhanLoaiDTO1).isNotEqualTo(nhomPhanLoaiDTO2);
        nhomPhanLoaiDTO1.setId(null);
        assertThat(nhomPhanLoaiDTO1).isNotEqualTo(nhomPhanLoaiDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nhomPhanLoaiMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nhomPhanLoaiMapper.fromId(null)).isNull();
    }
}
