package com.manager.common.web.rest;

import com.manager.common.CommonApp;

import com.manager.common.domain.NoiDung;
import com.manager.common.repository.NoiDungRepository;
import com.manager.common.service.NoiDungService;
import com.manager.common.service.dto.NoiDungDTO;
import com.manager.common.service.mapper.NoiDungMapper;
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
 * Test class for the NoiDungResource REST controller.
 *
 * @see NoiDungResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class NoiDungResourceIntTest {

    private static final String DEFAULT_NOI_DUNG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG_CODE = "BBBBBBBBBB";

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
    private NoiDungRepository noiDungRepository;

    @Autowired
    private NoiDungMapper noiDungMapper;

    @Autowired
    private NoiDungService noiDungService;

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

    private MockMvc restNoiDungMockMvc;

    private NoiDung noiDung;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NoiDungResource noiDungResource = new NoiDungResource(noiDungService);
        this.restNoiDungMockMvc = MockMvcBuilders.standaloneSetup(noiDungResource)
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
    public static NoiDung createEntity(EntityManager em) {
        NoiDung noiDung = new NoiDung()
            .noiDungCode(DEFAULT_NOI_DUNG_CODE)
            .userName(DEFAULT_USER_NAME)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME)
            .status(DEFAULT_STATUS)
            .program(DEFAULT_PROGRAM);
        return noiDung;
    }

    @Before
    public void initTest() {
        noiDung = createEntity(em);
    }

    @Test
    @Transactional
    public void createNoiDung() throws Exception {
        int databaseSizeBeforeCreate = noiDungRepository.findAll().size();

        // Create the NoiDung
        NoiDungDTO noiDungDTO = noiDungMapper.toDto(noiDung);
        restNoiDungMockMvc.perform(post("/api/noi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDTO)))
            .andExpect(status().isCreated());

        // Validate the NoiDung in the database
        List<NoiDung> noiDungList = noiDungRepository.findAll();
        assertThat(noiDungList).hasSize(databaseSizeBeforeCreate + 1);
        NoiDung testNoiDung = noiDungList.get(noiDungList.size() - 1);
        assertThat(testNoiDung.getNoiDungCode()).isEqualTo(DEFAULT_NOI_DUNG_CODE);
        assertThat(testNoiDung.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testNoiDung.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testNoiDung.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testNoiDung.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNoiDung.getProgram()).isEqualTo(DEFAULT_PROGRAM);
    }

    @Test
    @Transactional
    public void createNoiDungWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = noiDungRepository.findAll().size();

        // Create the NoiDung with an existing ID
        noiDung.setId(1L);
        NoiDungDTO noiDungDTO = noiDungMapper.toDto(noiDung);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoiDungMockMvc.perform(post("/api/noi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoiDung in the database
        List<NoiDung> noiDungList = noiDungRepository.findAll();
        assertThat(noiDungList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNoiDungCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noiDungRepository.findAll().size();
        // set the field null
        noiDung.setNoiDungCode(null);

        // Create the NoiDung, which fails.
        NoiDungDTO noiDungDTO = noiDungMapper.toDto(noiDung);

        restNoiDungMockMvc.perform(post("/api/noi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDTO)))
            .andExpect(status().isBadRequest());

        List<NoiDung> noiDungList = noiDungRepository.findAll();
        assertThat(noiDungList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = noiDungRepository.findAll().size();
        // set the field null
        noiDung.setUserName(null);

        // Create the NoiDung, which fails.
        NoiDungDTO noiDungDTO = noiDungMapper.toDto(noiDung);

        restNoiDungMockMvc.perform(post("/api/noi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDTO)))
            .andExpect(status().isBadRequest());

        List<NoiDung> noiDungList = noiDungRepository.findAll();
        assertThat(noiDungList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noiDungRepository.findAll().size();
        // set the field null
        noiDung.setCreateTime(null);

        // Create the NoiDung, which fails.
        NoiDungDTO noiDungDTO = noiDungMapper.toDto(noiDung);

        restNoiDungMockMvc.perform(post("/api/noi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDTO)))
            .andExpect(status().isBadRequest());

        List<NoiDung> noiDungList = noiDungRepository.findAll();
        assertThat(noiDungList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noiDungRepository.findAll().size();
        // set the field null
        noiDung.setUpdateTime(null);

        // Create the NoiDung, which fails.
        NoiDungDTO noiDungDTO = noiDungMapper.toDto(noiDung);

        restNoiDungMockMvc.perform(post("/api/noi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDTO)))
            .andExpect(status().isBadRequest());

        List<NoiDung> noiDungList = noiDungRepository.findAll();
        assertThat(noiDungList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = noiDungRepository.findAll().size();
        // set the field null
        noiDung.setStatus(null);

        // Create the NoiDung, which fails.
        NoiDungDTO noiDungDTO = noiDungMapper.toDto(noiDung);

        restNoiDungMockMvc.perform(post("/api/noi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDTO)))
            .andExpect(status().isBadRequest());

        List<NoiDung> noiDungList = noiDungRepository.findAll();
        assertThat(noiDungList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProgramIsRequired() throws Exception {
        int databaseSizeBeforeTest = noiDungRepository.findAll().size();
        // set the field null
        noiDung.setProgram(null);

        // Create the NoiDung, which fails.
        NoiDungDTO noiDungDTO = noiDungMapper.toDto(noiDung);

        restNoiDungMockMvc.perform(post("/api/noi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDTO)))
            .andExpect(status().isBadRequest());

        List<NoiDung> noiDungList = noiDungRepository.findAll();
        assertThat(noiDungList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNoiDungs() throws Exception {
        // Initialize the database
        noiDungRepository.saveAndFlush(noiDung);

        // Get all the noiDungList
        restNoiDungMockMvc.perform(get("/api/noi-dungs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noiDung.getId().intValue())))
            .andExpect(jsonPath("$.[*].noiDungCode").value(hasItem(DEFAULT_NOI_DUNG_CODE.toString())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(sameInstant(DEFAULT_CREATE_TIME))))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].program").value(hasItem(DEFAULT_PROGRAM.toString())));
    }
    
    @Test
    @Transactional
    public void getNoiDung() throws Exception {
        // Initialize the database
        noiDungRepository.saveAndFlush(noiDung);

        // Get the noiDung
        restNoiDungMockMvc.perform(get("/api/noi-dungs/{id}", noiDung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(noiDung.getId().intValue()))
            .andExpect(jsonPath("$.noiDungCode").value(DEFAULT_NOI_DUNG_CODE.toString()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.createTime").value(sameInstant(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.updateTime").value(sameInstant(DEFAULT_UPDATE_TIME)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.program").value(DEFAULT_PROGRAM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNoiDung() throws Exception {
        // Get the noiDung
        restNoiDungMockMvc.perform(get("/api/noi-dungs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNoiDung() throws Exception {
        // Initialize the database
        noiDungRepository.saveAndFlush(noiDung);

        int databaseSizeBeforeUpdate = noiDungRepository.findAll().size();

        // Update the noiDung
        NoiDung updatedNoiDung = noiDungRepository.findById(noiDung.getId()).get();
        // Disconnect from session so that the updates on updatedNoiDung are not directly saved in db
        em.detach(updatedNoiDung);
        updatedNoiDung
            .noiDungCode(UPDATED_NOI_DUNG_CODE)
            .userName(UPDATED_USER_NAME)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME)
            .status(UPDATED_STATUS)
            .program(UPDATED_PROGRAM);
        NoiDungDTO noiDungDTO = noiDungMapper.toDto(updatedNoiDung);

        restNoiDungMockMvc.perform(put("/api/noi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDTO)))
            .andExpect(status().isOk());

        // Validate the NoiDung in the database
        List<NoiDung> noiDungList = noiDungRepository.findAll();
        assertThat(noiDungList).hasSize(databaseSizeBeforeUpdate);
        NoiDung testNoiDung = noiDungList.get(noiDungList.size() - 1);
        assertThat(testNoiDung.getNoiDungCode()).isEqualTo(UPDATED_NOI_DUNG_CODE);
        assertThat(testNoiDung.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testNoiDung.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testNoiDung.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testNoiDung.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNoiDung.getProgram()).isEqualTo(UPDATED_PROGRAM);
    }

    @Test
    @Transactional
    public void updateNonExistingNoiDung() throws Exception {
        int databaseSizeBeforeUpdate = noiDungRepository.findAll().size();

        // Create the NoiDung
        NoiDungDTO noiDungDTO = noiDungMapper.toDto(noiDung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoiDungMockMvc.perform(put("/api/noi-dungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoiDung in the database
        List<NoiDung> noiDungList = noiDungRepository.findAll();
        assertThat(noiDungList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNoiDung() throws Exception {
        // Initialize the database
        noiDungRepository.saveAndFlush(noiDung);

        int databaseSizeBeforeDelete = noiDungRepository.findAll().size();

        // Delete the noiDung
        restNoiDungMockMvc.perform(delete("/api/noi-dungs/{id}", noiDung.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NoiDung> noiDungList = noiDungRepository.findAll();
        assertThat(noiDungList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoiDung.class);
        NoiDung noiDung1 = new NoiDung();
        noiDung1.setId(1L);
        NoiDung noiDung2 = new NoiDung();
        noiDung2.setId(noiDung1.getId());
        assertThat(noiDung1).isEqualTo(noiDung2);
        noiDung2.setId(2L);
        assertThat(noiDung1).isNotEqualTo(noiDung2);
        noiDung1.setId(null);
        assertThat(noiDung1).isNotEqualTo(noiDung2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoiDungDTO.class);
        NoiDungDTO noiDungDTO1 = new NoiDungDTO();
        noiDungDTO1.setId(1L);
        NoiDungDTO noiDungDTO2 = new NoiDungDTO();
        assertThat(noiDungDTO1).isNotEqualTo(noiDungDTO2);
        noiDungDTO2.setId(noiDungDTO1.getId());
        assertThat(noiDungDTO1).isEqualTo(noiDungDTO2);
        noiDungDTO2.setId(2L);
        assertThat(noiDungDTO1).isNotEqualTo(noiDungDTO2);
        noiDungDTO1.setId(null);
        assertThat(noiDungDTO1).isNotEqualTo(noiDungDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(noiDungMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(noiDungMapper.fromId(null)).isNull();
    }
}
