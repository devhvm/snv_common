package com.manager.common.web.rest;

import com.manager.common.CommonApp;

import com.manager.common.domain.CoQuanChuTri;
import com.manager.common.repository.CoQuanChuTriRepository;
import com.manager.common.service.CoQuanChuTriService;
import com.manager.common.service.dto.CoQuanChuTriDTO;
import com.manager.common.service.mapper.CoQuanChuTriMapper;
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
 * Test class for the CoQuanChuTriResource REST controller.
 *
 * @see CoQuanChuTriResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class CoQuanChuTriResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.PUBLISH;
    private static final Status UPDATED_STATUS = Status.UNPUBLISH;

    @Autowired
    private CoQuanChuTriRepository coQuanChuTriRepository;

    @Autowired
    private CoQuanChuTriMapper coQuanChuTriMapper;

    @Autowired
    private CoQuanChuTriService coQuanChuTriService;

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

    private MockMvc restCoQuanChuTriMockMvc;

    private CoQuanChuTri coQuanChuTri;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CoQuanChuTriResource coQuanChuTriResource = new CoQuanChuTriResource(coQuanChuTriService);
        this.restCoQuanChuTriMockMvc = MockMvcBuilders.standaloneSetup(coQuanChuTriResource)
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
    public static CoQuanChuTri createEntity(EntityManager em) {
        CoQuanChuTri coQuanChuTri = new CoQuanChuTri()
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS);
        return coQuanChuTri;
    }

    @Before
    public void initTest() {
        coQuanChuTri = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoQuanChuTri() throws Exception {
        int databaseSizeBeforeCreate = coQuanChuTriRepository.findAll().size();

        // Create the CoQuanChuTri
        CoQuanChuTriDTO coQuanChuTriDTO = coQuanChuTriMapper.toDto(coQuanChuTri);
        restCoQuanChuTriMockMvc.perform(post("/api/co-quan-chu-tris")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanChuTriDTO)))
            .andExpect(status().isCreated());

        // Validate the CoQuanChuTri in the database
        List<CoQuanChuTri> coQuanChuTriList = coQuanChuTriRepository.findAll();
        assertThat(coQuanChuTriList).hasSize(databaseSizeBeforeCreate + 1);
        CoQuanChuTri testCoQuanChuTri = coQuanChuTriList.get(coQuanChuTriList.size() - 1);
        assertThat(testCoQuanChuTri.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCoQuanChuTri.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCoQuanChuTriWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coQuanChuTriRepository.findAll().size();

        // Create the CoQuanChuTri with an existing ID
        coQuanChuTri.setId(1L);
        CoQuanChuTriDTO coQuanChuTriDTO = coQuanChuTriMapper.toDto(coQuanChuTri);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoQuanChuTriMockMvc.perform(post("/api/co-quan-chu-tris")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanChuTriDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoQuanChuTri in the database
        List<CoQuanChuTri> coQuanChuTriList = coQuanChuTriRepository.findAll();
        assertThat(coQuanChuTriList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = coQuanChuTriRepository.findAll().size();
        // set the field null
        coQuanChuTri.setName(null);

        // Create the CoQuanChuTri, which fails.
        CoQuanChuTriDTO coQuanChuTriDTO = coQuanChuTriMapper.toDto(coQuanChuTri);

        restCoQuanChuTriMockMvc.perform(post("/api/co-quan-chu-tris")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanChuTriDTO)))
            .andExpect(status().isBadRequest());

        List<CoQuanChuTri> coQuanChuTriList = coQuanChuTriRepository.findAll();
        assertThat(coQuanChuTriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = coQuanChuTriRepository.findAll().size();
        // set the field null
        coQuanChuTri.setStatus(null);

        // Create the CoQuanChuTri, which fails.
        CoQuanChuTriDTO coQuanChuTriDTO = coQuanChuTriMapper.toDto(coQuanChuTri);

        restCoQuanChuTriMockMvc.perform(post("/api/co-quan-chu-tris")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanChuTriDTO)))
            .andExpect(status().isBadRequest());

        List<CoQuanChuTri> coQuanChuTriList = coQuanChuTriRepository.findAll();
        assertThat(coQuanChuTriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCoQuanChuTris() throws Exception {
        // Initialize the database
        coQuanChuTriRepository.saveAndFlush(coQuanChuTri);

        // Get all the coQuanChuTriList
        restCoQuanChuTriMockMvc.perform(get("/api/co-quan-chu-tris?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coQuanChuTri.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getCoQuanChuTri() throws Exception {
        // Initialize the database
        coQuanChuTriRepository.saveAndFlush(coQuanChuTri);

        // Get the coQuanChuTri
        restCoQuanChuTriMockMvc.perform(get("/api/co-quan-chu-tris/{id}", coQuanChuTri.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coQuanChuTri.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCoQuanChuTri() throws Exception {
        // Get the coQuanChuTri
        restCoQuanChuTriMockMvc.perform(get("/api/co-quan-chu-tris/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoQuanChuTri() throws Exception {
        // Initialize the database
        coQuanChuTriRepository.saveAndFlush(coQuanChuTri);

        int databaseSizeBeforeUpdate = coQuanChuTriRepository.findAll().size();

        // Update the coQuanChuTri
        CoQuanChuTri updatedCoQuanChuTri = coQuanChuTriRepository.findById(coQuanChuTri.getId()).get();
        // Disconnect from session so that the updates on updatedCoQuanChuTri are not directly saved in db
        em.detach(updatedCoQuanChuTri);
        updatedCoQuanChuTri
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        CoQuanChuTriDTO coQuanChuTriDTO = coQuanChuTriMapper.toDto(updatedCoQuanChuTri);

        restCoQuanChuTriMockMvc.perform(put("/api/co-quan-chu-tris")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanChuTriDTO)))
            .andExpect(status().isOk());

        // Validate the CoQuanChuTri in the database
        List<CoQuanChuTri> coQuanChuTriList = coQuanChuTriRepository.findAll();
        assertThat(coQuanChuTriList).hasSize(databaseSizeBeforeUpdate);
        CoQuanChuTri testCoQuanChuTri = coQuanChuTriList.get(coQuanChuTriList.size() - 1);
        assertThat(testCoQuanChuTri.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCoQuanChuTri.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCoQuanChuTri() throws Exception {
        int databaseSizeBeforeUpdate = coQuanChuTriRepository.findAll().size();

        // Create the CoQuanChuTri
        CoQuanChuTriDTO coQuanChuTriDTO = coQuanChuTriMapper.toDto(coQuanChuTri);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoQuanChuTriMockMvc.perform(put("/api/co-quan-chu-tris")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coQuanChuTriDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoQuanChuTri in the database
        List<CoQuanChuTri> coQuanChuTriList = coQuanChuTriRepository.findAll();
        assertThat(coQuanChuTriList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCoQuanChuTri() throws Exception {
        // Initialize the database
        coQuanChuTriRepository.saveAndFlush(coQuanChuTri);

        int databaseSizeBeforeDelete = coQuanChuTriRepository.findAll().size();

        // Delete the coQuanChuTri
        restCoQuanChuTriMockMvc.perform(delete("/api/co-quan-chu-tris/{id}", coQuanChuTri.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CoQuanChuTri> coQuanChuTriList = coQuanChuTriRepository.findAll();
        assertThat(coQuanChuTriList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoQuanChuTri.class);
        CoQuanChuTri coQuanChuTri1 = new CoQuanChuTri();
        coQuanChuTri1.setId(1L);
        CoQuanChuTri coQuanChuTri2 = new CoQuanChuTri();
        coQuanChuTri2.setId(coQuanChuTri1.getId());
        assertThat(coQuanChuTri1).isEqualTo(coQuanChuTri2);
        coQuanChuTri2.setId(2L);
        assertThat(coQuanChuTri1).isNotEqualTo(coQuanChuTri2);
        coQuanChuTri1.setId(null);
        assertThat(coQuanChuTri1).isNotEqualTo(coQuanChuTri2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoQuanChuTriDTO.class);
        CoQuanChuTriDTO coQuanChuTriDTO1 = new CoQuanChuTriDTO();
        coQuanChuTriDTO1.setId(1L);
        CoQuanChuTriDTO coQuanChuTriDTO2 = new CoQuanChuTriDTO();
        assertThat(coQuanChuTriDTO1).isNotEqualTo(coQuanChuTriDTO2);
        coQuanChuTriDTO2.setId(coQuanChuTriDTO1.getId());
        assertThat(coQuanChuTriDTO1).isEqualTo(coQuanChuTriDTO2);
        coQuanChuTriDTO2.setId(2L);
        assertThat(coQuanChuTriDTO1).isNotEqualTo(coQuanChuTriDTO2);
        coQuanChuTriDTO1.setId(null);
        assertThat(coQuanChuTriDTO1).isNotEqualTo(coQuanChuTriDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(coQuanChuTriMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(coQuanChuTriMapper.fromId(null)).isNull();
    }
}
