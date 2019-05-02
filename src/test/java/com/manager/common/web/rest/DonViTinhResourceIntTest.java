package com.manager.common.web.rest;

import com.manager.common.CommonApp;
import com.manager.common.domain.DonViTinh;
import com.manager.common.domain.enumeration.Status;
import com.manager.common.repository.DonViTinhRepository;
import com.manager.common.service.DonViTinhService;
import com.manager.common.service.dto.DonViTinhDTO;
import com.manager.common.service.mapper.DonViTinhMapper;
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
 * Test class for the DonViTinhResource REST controller.
 *
 * @see DonViTinhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class DonViTinhResourceIntTest {

    private static final String DEFAULT_DON_VI_TINH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DON_VI_TINH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.PUBLISH;
    private static final Status UPDATED_STATUS = Status.UNPUBLISH;

    @Autowired
    private DonViTinhRepository donViTinhRepository;

    @Autowired
    private DonViTinhMapper donViTinhMapper;

    @Autowired
    private DonViTinhService donViTinhService;

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

    private MockMvc restDonViTinhMockMvc;

    private DonViTinh donViTinh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DonViTinhResource donViTinhResource = new DonViTinhResource(donViTinhService);
        this.restDonViTinhMockMvc = MockMvcBuilders.standaloneSetup(donViTinhResource)
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
    public static DonViTinh createEntity(EntityManager em) {
        DonViTinh donViTinh = new DonViTinh()
            .donViTinhCode(DEFAULT_DON_VI_TINH_CODE)
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS);
        return donViTinh;
    }

    @Before
    public void initTest() {
        donViTinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createDonViTinh() throws Exception {
        int databaseSizeBeforeCreate = donViTinhRepository.findAll().size();

        // Create the DonViTinh
        DonViTinhDTO donViTinhDTO = donViTinhMapper.toDto(donViTinh);
        restDonViTinhMockMvc.perform(post("/api/don-vi-tinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViTinhDTO)))
            .andExpect(status().isCreated());

        // Validate the DonViTinh in the database
        List<DonViTinh> donViTinhList = donViTinhRepository.findAll();
        assertThat(donViTinhList).hasSize(databaseSizeBeforeCreate + 1);
        DonViTinh testDonViTinh = donViTinhList.get(donViTinhList.size() - 1);
        assertThat(testDonViTinh.getDonViTinhCode()).isEqualTo(DEFAULT_DON_VI_TINH_CODE);
        assertThat(testDonViTinh.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDonViTinh.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createDonViTinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = donViTinhRepository.findAll().size();

        // Create the DonViTinh with an existing ID
        donViTinh.setId(1L);
        DonViTinhDTO donViTinhDTO = donViTinhMapper.toDto(donViTinh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDonViTinhMockMvc.perform(post("/api/don-vi-tinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViTinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DonViTinh in the database
        List<DonViTinh> donViTinhList = donViTinhRepository.findAll();
        assertThat(donViTinhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDonViTinhCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = donViTinhRepository.findAll().size();
        // set the field null
        donViTinh.setDonViTinhCode(null);

        // Create the DonViTinh, which fails.
        DonViTinhDTO donViTinhDTO = donViTinhMapper.toDto(donViTinh);

        restDonViTinhMockMvc.perform(post("/api/don-vi-tinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViTinhDTO)))
            .andExpect(status().isBadRequest());

        List<DonViTinh> donViTinhList = donViTinhRepository.findAll();
        assertThat(donViTinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = donViTinhRepository.findAll().size();
        // set the field null
        donViTinh.setName(null);

        // Create the DonViTinh, which fails.
        DonViTinhDTO donViTinhDTO = donViTinhMapper.toDto(donViTinh);

        restDonViTinhMockMvc.perform(post("/api/don-vi-tinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViTinhDTO)))
            .andExpect(status().isBadRequest());

        List<DonViTinh> donViTinhList = donViTinhRepository.findAll();
        assertThat(donViTinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = donViTinhRepository.findAll().size();
        // set the field null
        donViTinh.setStatus(null);

        // Create the DonViTinh, which fails.
        DonViTinhDTO donViTinhDTO = donViTinhMapper.toDto(donViTinh);

        restDonViTinhMockMvc.perform(post("/api/don-vi-tinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViTinhDTO)))
            .andExpect(status().isBadRequest());

        List<DonViTinh> donViTinhList = donViTinhRepository.findAll();
        assertThat(donViTinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDonViTinhs() throws Exception {
        // Initialize the database
        donViTinhRepository.saveAndFlush(donViTinh);

        // Get all the donViTinhList
        restDonViTinhMockMvc.perform(get("/api/don-vi-tinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donViTinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].donViTinhCode").value(hasItem(DEFAULT_DON_VI_TINH_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getDonViTinh() throws Exception {
        // Initialize the database
        donViTinhRepository.saveAndFlush(donViTinh);

        // Get the donViTinh
        restDonViTinhMockMvc.perform(get("/api/don-vi-tinhs/{id}", donViTinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(donViTinh.getId().intValue()))
            .andExpect(jsonPath("$.donViTinhCode").value(DEFAULT_DON_VI_TINH_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDonViTinh() throws Exception {
        // Get the donViTinh
        restDonViTinhMockMvc.perform(get("/api/don-vi-tinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDonViTinh() throws Exception {
        // Initialize the database
        donViTinhRepository.saveAndFlush(donViTinh);

        int databaseSizeBeforeUpdate = donViTinhRepository.findAll().size();

        // Update the donViTinh
        DonViTinh updatedDonViTinh = donViTinhRepository.findById(donViTinh.getId()).get();
        // Disconnect from session so that the updates on updatedDonViTinh are not directly saved in db
        em.detach(updatedDonViTinh);
        updatedDonViTinh
            .donViTinhCode(UPDATED_DON_VI_TINH_CODE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        DonViTinhDTO donViTinhDTO = donViTinhMapper.toDto(updatedDonViTinh);

        restDonViTinhMockMvc.perform(put("/api/don-vi-tinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViTinhDTO)))
            .andExpect(status().isOk());

        // Validate the DonViTinh in the database
        List<DonViTinh> donViTinhList = donViTinhRepository.findAll();
        assertThat(donViTinhList).hasSize(databaseSizeBeforeUpdate);
        DonViTinh testDonViTinh = donViTinhList.get(donViTinhList.size() - 1);
        assertThat(testDonViTinh.getDonViTinhCode()).isEqualTo(UPDATED_DON_VI_TINH_CODE);
        assertThat(testDonViTinh.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDonViTinh.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingDonViTinh() throws Exception {
        int databaseSizeBeforeUpdate = donViTinhRepository.findAll().size();

        // Create the DonViTinh
        DonViTinhDTO donViTinhDTO = donViTinhMapper.toDto(donViTinh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDonViTinhMockMvc.perform(put("/api/don-vi-tinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(donViTinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DonViTinh in the database
        List<DonViTinh> donViTinhList = donViTinhRepository.findAll();
        assertThat(donViTinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDonViTinh() throws Exception {
        // Initialize the database
        donViTinhRepository.saveAndFlush(donViTinh);

        int databaseSizeBeforeDelete = donViTinhRepository.findAll().size();

        // Delete the donViTinh
        restDonViTinhMockMvc.perform(delete("/api/don-vi-tinhs/{id}", donViTinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DonViTinh> donViTinhList = donViTinhRepository.findAll();
        assertThat(donViTinhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DonViTinh.class);
        DonViTinh donViTinh1 = new DonViTinh();
        donViTinh1.setId(1L);
        DonViTinh donViTinh2 = new DonViTinh();
        donViTinh2.setId(donViTinh1.getId());
        assertThat(donViTinh1).isEqualTo(donViTinh2);
        donViTinh2.setId(2L);
        assertThat(donViTinh1).isNotEqualTo(donViTinh2);
        donViTinh1.setId(null);
        assertThat(donViTinh1).isNotEqualTo(donViTinh2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DonViTinhDTO.class);
        DonViTinhDTO donViTinhDTO1 = new DonViTinhDTO();
        donViTinhDTO1.setId(1L);
        DonViTinhDTO donViTinhDTO2 = new DonViTinhDTO();
        assertThat(donViTinhDTO1).isNotEqualTo(donViTinhDTO2);
        donViTinhDTO2.setId(donViTinhDTO1.getId());
        assertThat(donViTinhDTO1).isEqualTo(donViTinhDTO2);
        donViTinhDTO2.setId(2L);
        assertThat(donViTinhDTO1).isNotEqualTo(donViTinhDTO2);
        donViTinhDTO1.setId(null);
        assertThat(donViTinhDTO1).isNotEqualTo(donViTinhDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(donViTinhMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(donViTinhMapper.fromId(null)).isNull();
    }
}
