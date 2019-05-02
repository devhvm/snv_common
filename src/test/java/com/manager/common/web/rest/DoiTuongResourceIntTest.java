package com.manager.common.web.rest;

import com.manager.common.CommonApp;
import com.manager.common.domain.DoiTuong;
import com.manager.common.domain.enumeration.Status;
import com.manager.common.repository.DoiTuongRepository;
import com.manager.common.service.DoiTuongService;
import com.manager.common.service.dto.DoiTuongDTO;
import com.manager.common.service.mapper.DoiTuongMapper;
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
 * Test class for the DoiTuongResource REST controller.
 *
 * @see DoiTuongResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class DoiTuongResourceIntTest {

    private static final String DEFAULT_DOI_TUONG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DOI_TUONG_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.PUBLISH;
    private static final Status UPDATED_STATUS = Status.UNPUBLISH;

    @Autowired
    private DoiTuongRepository doiTuongRepository;

    @Autowired
    private DoiTuongMapper doiTuongMapper;

    @Autowired
    private DoiTuongService doiTuongService;

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

    private MockMvc restDoiTuongMockMvc;

    private DoiTuong doiTuong;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DoiTuongResource doiTuongResource = new DoiTuongResource(doiTuongService);
        this.restDoiTuongMockMvc = MockMvcBuilders.standaloneSetup(doiTuongResource)
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
    public static DoiTuong createEntity(EntityManager em) {
        DoiTuong doiTuong = new DoiTuong()
            .doiTuongCode(DEFAULT_DOI_TUONG_CODE)
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS);
        return doiTuong;
    }

    @Before
    public void initTest() {
        doiTuong = createEntity(em);
    }

    @Test
    @Transactional
    public void createDoiTuong() throws Exception {
        int databaseSizeBeforeCreate = doiTuongRepository.findAll().size();

        // Create the DoiTuong
        DoiTuongDTO doiTuongDTO = doiTuongMapper.toDto(doiTuong);
        restDoiTuongMockMvc.perform(post("/api/doi-tuongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doiTuongDTO)))
            .andExpect(status().isCreated());

        // Validate the DoiTuong in the database
        List<DoiTuong> doiTuongList = doiTuongRepository.findAll();
        assertThat(doiTuongList).hasSize(databaseSizeBeforeCreate + 1);
        DoiTuong testDoiTuong = doiTuongList.get(doiTuongList.size() - 1);
        assertThat(testDoiTuong.getDoiTuongCode()).isEqualTo(DEFAULT_DOI_TUONG_CODE);
        assertThat(testDoiTuong.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDoiTuong.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createDoiTuongWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = doiTuongRepository.findAll().size();

        // Create the DoiTuong with an existing ID
        doiTuong.setId(1L);
        DoiTuongDTO doiTuongDTO = doiTuongMapper.toDto(doiTuong);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDoiTuongMockMvc.perform(post("/api/doi-tuongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doiTuongDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DoiTuong in the database
        List<DoiTuong> doiTuongList = doiTuongRepository.findAll();
        assertThat(doiTuongList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDoiTuongCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = doiTuongRepository.findAll().size();
        // set the field null
        doiTuong.setDoiTuongCode(null);

        // Create the DoiTuong, which fails.
        DoiTuongDTO doiTuongDTO = doiTuongMapper.toDto(doiTuong);

        restDoiTuongMockMvc.perform(post("/api/doi-tuongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doiTuongDTO)))
            .andExpect(status().isBadRequest());

        List<DoiTuong> doiTuongList = doiTuongRepository.findAll();
        assertThat(doiTuongList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = doiTuongRepository.findAll().size();
        // set the field null
        doiTuong.setName(null);

        // Create the DoiTuong, which fails.
        DoiTuongDTO doiTuongDTO = doiTuongMapper.toDto(doiTuong);

        restDoiTuongMockMvc.perform(post("/api/doi-tuongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doiTuongDTO)))
            .andExpect(status().isBadRequest());

        List<DoiTuong> doiTuongList = doiTuongRepository.findAll();
        assertThat(doiTuongList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = doiTuongRepository.findAll().size();
        // set the field null
        doiTuong.setStatus(null);

        // Create the DoiTuong, which fails.
        DoiTuongDTO doiTuongDTO = doiTuongMapper.toDto(doiTuong);

        restDoiTuongMockMvc.perform(post("/api/doi-tuongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doiTuongDTO)))
            .andExpect(status().isBadRequest());

        List<DoiTuong> doiTuongList = doiTuongRepository.findAll();
        assertThat(doiTuongList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDoiTuongs() throws Exception {
        // Initialize the database
        doiTuongRepository.saveAndFlush(doiTuong);

        // Get all the doiTuongList
        restDoiTuongMockMvc.perform(get("/api/doi-tuongs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(doiTuong.getId().intValue())))
            .andExpect(jsonPath("$.[*].doiTuongCode").value(hasItem(DEFAULT_DOI_TUONG_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getDoiTuong() throws Exception {
        // Initialize the database
        doiTuongRepository.saveAndFlush(doiTuong);

        // Get the doiTuong
        restDoiTuongMockMvc.perform(get("/api/doi-tuongs/{id}", doiTuong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(doiTuong.getId().intValue()))
            .andExpect(jsonPath("$.doiTuongCode").value(DEFAULT_DOI_TUONG_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDoiTuong() throws Exception {
        // Get the doiTuong
        restDoiTuongMockMvc.perform(get("/api/doi-tuongs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDoiTuong() throws Exception {
        // Initialize the database
        doiTuongRepository.saveAndFlush(doiTuong);

        int databaseSizeBeforeUpdate = doiTuongRepository.findAll().size();

        // Update the doiTuong
        DoiTuong updatedDoiTuong = doiTuongRepository.findById(doiTuong.getId()).get();
        // Disconnect from session so that the updates on updatedDoiTuong are not directly saved in db
        em.detach(updatedDoiTuong);
        updatedDoiTuong
            .doiTuongCode(UPDATED_DOI_TUONG_CODE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        DoiTuongDTO doiTuongDTO = doiTuongMapper.toDto(updatedDoiTuong);

        restDoiTuongMockMvc.perform(put("/api/doi-tuongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doiTuongDTO)))
            .andExpect(status().isOk());

        // Validate the DoiTuong in the database
        List<DoiTuong> doiTuongList = doiTuongRepository.findAll();
        assertThat(doiTuongList).hasSize(databaseSizeBeforeUpdate);
        DoiTuong testDoiTuong = doiTuongList.get(doiTuongList.size() - 1);
        assertThat(testDoiTuong.getDoiTuongCode()).isEqualTo(UPDATED_DOI_TUONG_CODE);
        assertThat(testDoiTuong.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDoiTuong.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingDoiTuong() throws Exception {
        int databaseSizeBeforeUpdate = doiTuongRepository.findAll().size();

        // Create the DoiTuong
        DoiTuongDTO doiTuongDTO = doiTuongMapper.toDto(doiTuong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDoiTuongMockMvc.perform(put("/api/doi-tuongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doiTuongDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DoiTuong in the database
        List<DoiTuong> doiTuongList = doiTuongRepository.findAll();
        assertThat(doiTuongList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDoiTuong() throws Exception {
        // Initialize the database
        doiTuongRepository.saveAndFlush(doiTuong);

        int databaseSizeBeforeDelete = doiTuongRepository.findAll().size();

        // Delete the doiTuong
        restDoiTuongMockMvc.perform(delete("/api/doi-tuongs/{id}", doiTuong.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DoiTuong> doiTuongList = doiTuongRepository.findAll();
        assertThat(doiTuongList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoiTuong.class);
        DoiTuong doiTuong1 = new DoiTuong();
        doiTuong1.setId(1L);
        DoiTuong doiTuong2 = new DoiTuong();
        doiTuong2.setId(doiTuong1.getId());
        assertThat(doiTuong1).isEqualTo(doiTuong2);
        doiTuong2.setId(2L);
        assertThat(doiTuong1).isNotEqualTo(doiTuong2);
        doiTuong1.setId(null);
        assertThat(doiTuong1).isNotEqualTo(doiTuong2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoiTuongDTO.class);
        DoiTuongDTO doiTuongDTO1 = new DoiTuongDTO();
        doiTuongDTO1.setId(1L);
        DoiTuongDTO doiTuongDTO2 = new DoiTuongDTO();
        assertThat(doiTuongDTO1).isNotEqualTo(doiTuongDTO2);
        doiTuongDTO2.setId(doiTuongDTO1.getId());
        assertThat(doiTuongDTO1).isEqualTo(doiTuongDTO2);
        doiTuongDTO2.setId(2L);
        assertThat(doiTuongDTO1).isNotEqualTo(doiTuongDTO2);
        doiTuongDTO1.setId(null);
        assertThat(doiTuongDTO1).isNotEqualTo(doiTuongDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(doiTuongMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(doiTuongMapper.fromId(null)).isNull();
    }
}
