package com.manager.common.web.rest;

import com.manager.common.CommonApp;
import com.manager.common.domain.TieuChi;
import com.manager.common.domain.enumeration.Status;
import com.manager.common.repository.TieuChiRepository;
import com.manager.common.service.TieuChiService;
import com.manager.common.service.dto.TieuChiDTO;
import com.manager.common.service.mapper.TieuChiMapper;
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
 * Test class for the TieuChiResource REST controller.
 *
 * @see TieuChiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class TieuChiResourceIntTest {

    private static final Status DEFAULT_STATUS = Status.PUBLISH;
    private static final Status UPDATED_STATUS = Status.UNPUBLISH;

    @Autowired
    private TieuChiRepository tieuChiRepository;

    @Autowired
    private TieuChiMapper tieuChiMapper;

    @Autowired
    private TieuChiService tieuChiService;

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

    private MockMvc restTieuChiMockMvc;

    private TieuChi tieuChi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TieuChiResource tieuChiResource = new TieuChiResource(tieuChiService);
        this.restTieuChiMockMvc = MockMvcBuilders.standaloneSetup(tieuChiResource)
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
    public static TieuChi createEntity(EntityManager em) {
        TieuChi tieuChi = new TieuChi()
            .status(DEFAULT_STATUS);
        return tieuChi;
    }

    @Before
    public void initTest() {
        tieuChi = createEntity(em);
    }

    @Test
    @Transactional
    public void createTieuChi() throws Exception {
        int databaseSizeBeforeCreate = tieuChiRepository.findAll().size();

        // Create the TieuChi
        TieuChiDTO tieuChiDTO = tieuChiMapper.toDto(tieuChi);
        restTieuChiMockMvc.perform(post("/api/tieu-chis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tieuChiDTO)))
            .andExpect(status().isCreated());

        // Validate the TieuChi in the database
        List<TieuChi> tieuChiList = tieuChiRepository.findAll();
        assertThat(tieuChiList).hasSize(databaseSizeBeforeCreate + 1);
        TieuChi testTieuChi = tieuChiList.get(tieuChiList.size() - 1);
        assertThat(testTieuChi.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createTieuChiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tieuChiRepository.findAll().size();

        // Create the TieuChi with an existing ID
        tieuChi.setId(1L);
        TieuChiDTO tieuChiDTO = tieuChiMapper.toDto(tieuChi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTieuChiMockMvc.perform(post("/api/tieu-chis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tieuChiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TieuChi in the database
        List<TieuChi> tieuChiList = tieuChiRepository.findAll();
        assertThat(tieuChiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = tieuChiRepository.findAll().size();
        // set the field null
        tieuChi.setStatus(null);

        // Create the TieuChi, which fails.
        TieuChiDTO tieuChiDTO = tieuChiMapper.toDto(tieuChi);

        restTieuChiMockMvc.perform(post("/api/tieu-chis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tieuChiDTO)))
            .andExpect(status().isBadRequest());

        List<TieuChi> tieuChiList = tieuChiRepository.findAll();
        assertThat(tieuChiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTieuChis() throws Exception {
        // Initialize the database
        tieuChiRepository.saveAndFlush(tieuChi);

        // Get all the tieuChiList
        restTieuChiMockMvc.perform(get("/api/tieu-chis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tieuChi.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getTieuChi() throws Exception {
        // Initialize the database
        tieuChiRepository.saveAndFlush(tieuChi);

        // Get the tieuChi
        restTieuChiMockMvc.perform(get("/api/tieu-chis/{id}", tieuChi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tieuChi.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTieuChi() throws Exception {
        // Get the tieuChi
        restTieuChiMockMvc.perform(get("/api/tieu-chis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTieuChi() throws Exception {
        // Initialize the database
        tieuChiRepository.saveAndFlush(tieuChi);

        int databaseSizeBeforeUpdate = tieuChiRepository.findAll().size();

        // Update the tieuChi
        TieuChi updatedTieuChi = tieuChiRepository.findById(tieuChi.getId()).get();
        // Disconnect from session so that the updates on updatedTieuChi are not directly saved in db
        em.detach(updatedTieuChi);
        updatedTieuChi
            .status(UPDATED_STATUS);
        TieuChiDTO tieuChiDTO = tieuChiMapper.toDto(updatedTieuChi);

        restTieuChiMockMvc.perform(put("/api/tieu-chis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tieuChiDTO)))
            .andExpect(status().isOk());

        // Validate the TieuChi in the database
        List<TieuChi> tieuChiList = tieuChiRepository.findAll();
        assertThat(tieuChiList).hasSize(databaseSizeBeforeUpdate);
        TieuChi testTieuChi = tieuChiList.get(tieuChiList.size() - 1);
        assertThat(testTieuChi.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingTieuChi() throws Exception {
        int databaseSizeBeforeUpdate = tieuChiRepository.findAll().size();

        // Create the TieuChi
        TieuChiDTO tieuChiDTO = tieuChiMapper.toDto(tieuChi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTieuChiMockMvc.perform(put("/api/tieu-chis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tieuChiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TieuChi in the database
        List<TieuChi> tieuChiList = tieuChiRepository.findAll();
        assertThat(tieuChiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTieuChi() throws Exception {
        // Initialize the database
        tieuChiRepository.saveAndFlush(tieuChi);

        int databaseSizeBeforeDelete = tieuChiRepository.findAll().size();

        // Delete the tieuChi
        restTieuChiMockMvc.perform(delete("/api/tieu-chis/{id}", tieuChi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TieuChi> tieuChiList = tieuChiRepository.findAll();
        assertThat(tieuChiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TieuChi.class);
        TieuChi tieuChi1 = new TieuChi();
        tieuChi1.setId(1L);
        TieuChi tieuChi2 = new TieuChi();
        tieuChi2.setId(tieuChi1.getId());
        assertThat(tieuChi1).isEqualTo(tieuChi2);
        tieuChi2.setId(2L);
        assertThat(tieuChi1).isNotEqualTo(tieuChi2);
        tieuChi1.setId(null);
        assertThat(tieuChi1).isNotEqualTo(tieuChi2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TieuChiDTO.class);
        TieuChiDTO tieuChiDTO1 = new TieuChiDTO();
        tieuChiDTO1.setId(1L);
        TieuChiDTO tieuChiDTO2 = new TieuChiDTO();
        assertThat(tieuChiDTO1).isNotEqualTo(tieuChiDTO2);
        tieuChiDTO2.setId(tieuChiDTO1.getId());
        assertThat(tieuChiDTO1).isEqualTo(tieuChiDTO2);
        tieuChiDTO2.setId(2L);
        assertThat(tieuChiDTO1).isNotEqualTo(tieuChiDTO2);
        tieuChiDTO1.setId(null);
        assertThat(tieuChiDTO1).isNotEqualTo(tieuChiDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tieuChiMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tieuChiMapper.fromId(null)).isNull();
    }
}
