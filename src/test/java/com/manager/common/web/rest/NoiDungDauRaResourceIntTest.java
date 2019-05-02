package com.manager.common.web.rest;

import com.manager.common.CommonApp;
import com.manager.common.domain.NoiDungDauRa;
import com.manager.common.domain.enumeration.Status;
import com.manager.common.repository.NoiDungDauRaRepository;
import com.manager.common.service.NoiDungDauRaService;
import com.manager.common.service.dto.NoiDungDauRaDTO;
import com.manager.common.service.mapper.NoiDungDauRaMapper;
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
 * Test class for the NoiDungDauRaResource REST controller.
 *
 * @see NoiDungDauRaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class NoiDungDauRaResourceIntTest {

    private static final Status DEFAULT_STATUS = Status.PUBLISH;
    private static final Status UPDATED_STATUS = Status.UNPUBLISH;

    @Autowired
    private NoiDungDauRaRepository noiDungDauRaRepository;

    @Autowired
    private NoiDungDauRaMapper noiDungDauRaMapper;

    @Autowired
    private NoiDungDauRaService noiDungDauRaService;

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

    private MockMvc restNoiDungDauRaMockMvc;

    private NoiDungDauRa noiDungDauRa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NoiDungDauRaResource noiDungDauRaResource = new NoiDungDauRaResource(noiDungDauRaService);
        this.restNoiDungDauRaMockMvc = MockMvcBuilders.standaloneSetup(noiDungDauRaResource)
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
    public static NoiDungDauRa createEntity(EntityManager em) {
        NoiDungDauRa noiDungDauRa = new NoiDungDauRa()
            .status(DEFAULT_STATUS);
        return noiDungDauRa;
    }

    @Before
    public void initTest() {
        noiDungDauRa = createEntity(em);
    }

    @Test
    @Transactional
    public void createNoiDungDauRa() throws Exception {
        int databaseSizeBeforeCreate = noiDungDauRaRepository.findAll().size();

        // Create the NoiDungDauRa
        NoiDungDauRaDTO noiDungDauRaDTO = noiDungDauRaMapper.toDto(noiDungDauRa);
        restNoiDungDauRaMockMvc.perform(post("/api/noi-dung-dau-ras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDauRaDTO)))
            .andExpect(status().isCreated());

        // Validate the NoiDungDauRa in the database
        List<NoiDungDauRa> noiDungDauRaList = noiDungDauRaRepository.findAll();
        assertThat(noiDungDauRaList).hasSize(databaseSizeBeforeCreate + 1);
        NoiDungDauRa testNoiDungDauRa = noiDungDauRaList.get(noiDungDauRaList.size() - 1);
        assertThat(testNoiDungDauRa.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createNoiDungDauRaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = noiDungDauRaRepository.findAll().size();

        // Create the NoiDungDauRa with an existing ID
        noiDungDauRa.setId(1L);
        NoiDungDauRaDTO noiDungDauRaDTO = noiDungDauRaMapper.toDto(noiDungDauRa);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoiDungDauRaMockMvc.perform(post("/api/noi-dung-dau-ras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDauRaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoiDungDauRa in the database
        List<NoiDungDauRa> noiDungDauRaList = noiDungDauRaRepository.findAll();
        assertThat(noiDungDauRaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = noiDungDauRaRepository.findAll().size();
        // set the field null
        noiDungDauRa.setStatus(null);

        // Create the NoiDungDauRa, which fails.
        NoiDungDauRaDTO noiDungDauRaDTO = noiDungDauRaMapper.toDto(noiDungDauRa);

        restNoiDungDauRaMockMvc.perform(post("/api/noi-dung-dau-ras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDauRaDTO)))
            .andExpect(status().isBadRequest());

        List<NoiDungDauRa> noiDungDauRaList = noiDungDauRaRepository.findAll();
        assertThat(noiDungDauRaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNoiDungDauRas() throws Exception {
        // Initialize the database
        noiDungDauRaRepository.saveAndFlush(noiDungDauRa);

        // Get all the noiDungDauRaList
        restNoiDungDauRaMockMvc.perform(get("/api/noi-dung-dau-ras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noiDungDauRa.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getNoiDungDauRa() throws Exception {
        // Initialize the database
        noiDungDauRaRepository.saveAndFlush(noiDungDauRa);

        // Get the noiDungDauRa
        restNoiDungDauRaMockMvc.perform(get("/api/noi-dung-dau-ras/{id}", noiDungDauRa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(noiDungDauRa.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNoiDungDauRa() throws Exception {
        // Get the noiDungDauRa
        restNoiDungDauRaMockMvc.perform(get("/api/noi-dung-dau-ras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNoiDungDauRa() throws Exception {
        // Initialize the database
        noiDungDauRaRepository.saveAndFlush(noiDungDauRa);

        int databaseSizeBeforeUpdate = noiDungDauRaRepository.findAll().size();

        // Update the noiDungDauRa
        NoiDungDauRa updatedNoiDungDauRa = noiDungDauRaRepository.findById(noiDungDauRa.getId()).get();
        // Disconnect from session so that the updates on updatedNoiDungDauRa are not directly saved in db
        em.detach(updatedNoiDungDauRa);
        updatedNoiDungDauRa
            .status(UPDATED_STATUS);
        NoiDungDauRaDTO noiDungDauRaDTO = noiDungDauRaMapper.toDto(updatedNoiDungDauRa);

        restNoiDungDauRaMockMvc.perform(put("/api/noi-dung-dau-ras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDauRaDTO)))
            .andExpect(status().isOk());

        // Validate the NoiDungDauRa in the database
        List<NoiDungDauRa> noiDungDauRaList = noiDungDauRaRepository.findAll();
        assertThat(noiDungDauRaList).hasSize(databaseSizeBeforeUpdate);
        NoiDungDauRa testNoiDungDauRa = noiDungDauRaList.get(noiDungDauRaList.size() - 1);
        assertThat(testNoiDungDauRa.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingNoiDungDauRa() throws Exception {
        int databaseSizeBeforeUpdate = noiDungDauRaRepository.findAll().size();

        // Create the NoiDungDauRa
        NoiDungDauRaDTO noiDungDauRaDTO = noiDungDauRaMapper.toDto(noiDungDauRa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoiDungDauRaMockMvc.perform(put("/api/noi-dung-dau-ras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDauRaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoiDungDauRa in the database
        List<NoiDungDauRa> noiDungDauRaList = noiDungDauRaRepository.findAll();
        assertThat(noiDungDauRaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNoiDungDauRa() throws Exception {
        // Initialize the database
        noiDungDauRaRepository.saveAndFlush(noiDungDauRa);

        int databaseSizeBeforeDelete = noiDungDauRaRepository.findAll().size();

        // Delete the noiDungDauRa
        restNoiDungDauRaMockMvc.perform(delete("/api/noi-dung-dau-ras/{id}", noiDungDauRa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NoiDungDauRa> noiDungDauRaList = noiDungDauRaRepository.findAll();
        assertThat(noiDungDauRaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoiDungDauRa.class);
        NoiDungDauRa noiDungDauRa1 = new NoiDungDauRa();
        noiDungDauRa1.setId(1L);
        NoiDungDauRa noiDungDauRa2 = new NoiDungDauRa();
        noiDungDauRa2.setId(noiDungDauRa1.getId());
        assertThat(noiDungDauRa1).isEqualTo(noiDungDauRa2);
        noiDungDauRa2.setId(2L);
        assertThat(noiDungDauRa1).isNotEqualTo(noiDungDauRa2);
        noiDungDauRa1.setId(null);
        assertThat(noiDungDauRa1).isNotEqualTo(noiDungDauRa2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoiDungDauRaDTO.class);
        NoiDungDauRaDTO noiDungDauRaDTO1 = new NoiDungDauRaDTO();
        noiDungDauRaDTO1.setId(1L);
        NoiDungDauRaDTO noiDungDauRaDTO2 = new NoiDungDauRaDTO();
        assertThat(noiDungDauRaDTO1).isNotEqualTo(noiDungDauRaDTO2);
        noiDungDauRaDTO2.setId(noiDungDauRaDTO1.getId());
        assertThat(noiDungDauRaDTO1).isEqualTo(noiDungDauRaDTO2);
        noiDungDauRaDTO2.setId(2L);
        assertThat(noiDungDauRaDTO1).isNotEqualTo(noiDungDauRaDTO2);
        noiDungDauRaDTO1.setId(null);
        assertThat(noiDungDauRaDTO1).isNotEqualTo(noiDungDauRaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(noiDungDauRaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(noiDungDauRaMapper.fromId(null)).isNull();
    }
}
