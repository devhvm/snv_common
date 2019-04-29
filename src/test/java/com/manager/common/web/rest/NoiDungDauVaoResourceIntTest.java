package com.manager.common.web.rest;

import com.manager.common.CommonApp;
import com.manager.common.domain.NoiDungDauVao;
import com.manager.common.domain.enumeration.Status;
import com.manager.common.repository.NoiDungDauVaoRepository;
import com.manager.common.service.NoiDungDauVaoService;
import com.manager.common.service.dto.NoiDungDauVaoDTO;
import com.manager.common.service.mapper.NoiDungDauVaoMapper;
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
 * Test class for the NoiDungDauVaoResource REST controller.
 *
 * @see NoiDungDauVaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApp.class)
public class NoiDungDauVaoResourceIntTest {

    private static final Status DEFAULT_STATUS = Status.PUBLISH;
    private static final Status UPDATED_STATUS = Status.UNPUBLISH;

    @Autowired
    private NoiDungDauVaoRepository noiDungDauVaoRepository;

    @Autowired
    private NoiDungDauVaoMapper noiDungDauVaoMapper;

    @Autowired
    private NoiDungDauVaoService noiDungDauVaoService;

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

    private MockMvc restNoiDungDauVaoMockMvc;

    private NoiDungDauVao noiDungDauVao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NoiDungDauVaoResource noiDungDauVaoResource = new NoiDungDauVaoResource(noiDungDauVaoService);
        this.restNoiDungDauVaoMockMvc = MockMvcBuilders.standaloneSetup(noiDungDauVaoResource)
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
    public static NoiDungDauVao createEntity(EntityManager em) {
        NoiDungDauVao noiDungDauVao = new NoiDungDauVao()
            .status(DEFAULT_STATUS);
        return noiDungDauVao;
    }

    @Before
    public void initTest() {
        noiDungDauVao = createEntity(em);
    }

    @Test
    @Transactional
    public void createNoiDungDauVao() throws Exception {
        int databaseSizeBeforeCreate = noiDungDauVaoRepository.findAll().size();

        // Create the NoiDungDauVao
        NoiDungDauVaoDTO noiDungDauVaoDTO = noiDungDauVaoMapper.toDto(noiDungDauVao);
        restNoiDungDauVaoMockMvc.perform(post("/api/noi-dung-dau-vaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDauVaoDTO)))
            .andExpect(status().isCreated());

        // Validate the NoiDungDauVao in the database
        List<NoiDungDauVao> noiDungDauVaoList = noiDungDauVaoRepository.findAll();
        assertThat(noiDungDauVaoList).hasSize(databaseSizeBeforeCreate + 1);
        NoiDungDauVao testNoiDungDauVao = noiDungDauVaoList.get(noiDungDauVaoList.size() - 1);
        assertThat(testNoiDungDauVao.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createNoiDungDauVaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = noiDungDauVaoRepository.findAll().size();

        // Create the NoiDungDauVao with an existing ID
        noiDungDauVao.setId(1L);
        NoiDungDauVaoDTO noiDungDauVaoDTO = noiDungDauVaoMapper.toDto(noiDungDauVao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoiDungDauVaoMockMvc.perform(post("/api/noi-dung-dau-vaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDauVaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoiDungDauVao in the database
        List<NoiDungDauVao> noiDungDauVaoList = noiDungDauVaoRepository.findAll();
        assertThat(noiDungDauVaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = noiDungDauVaoRepository.findAll().size();
        // set the field null
        noiDungDauVao.setStatus(null);

        // Create the NoiDungDauVao, which fails.
        NoiDungDauVaoDTO noiDungDauVaoDTO = noiDungDauVaoMapper.toDto(noiDungDauVao);

        restNoiDungDauVaoMockMvc.perform(post("/api/noi-dung-dau-vaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDauVaoDTO)))
            .andExpect(status().isBadRequest());

        List<NoiDungDauVao> noiDungDauVaoList = noiDungDauVaoRepository.findAll();
        assertThat(noiDungDauVaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNoiDungDauVaos() throws Exception {
        // Initialize the database
        noiDungDauVaoRepository.saveAndFlush(noiDungDauVao);

        // Get all the noiDungDauVaoList
        restNoiDungDauVaoMockMvc.perform(get("/api/noi-dung-dau-vaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noiDungDauVao.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getNoiDungDauVao() throws Exception {
        // Initialize the database
        noiDungDauVaoRepository.saveAndFlush(noiDungDauVao);

        // Get the noiDungDauVao
        restNoiDungDauVaoMockMvc.perform(get("/api/noi-dung-dau-vaos/{id}", noiDungDauVao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(noiDungDauVao.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNoiDungDauVao() throws Exception {
        // Get the noiDungDauVao
        restNoiDungDauVaoMockMvc.perform(get("/api/noi-dung-dau-vaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNoiDungDauVao() throws Exception {
        // Initialize the database
        noiDungDauVaoRepository.saveAndFlush(noiDungDauVao);

        int databaseSizeBeforeUpdate = noiDungDauVaoRepository.findAll().size();

        // Update the noiDungDauVao
        NoiDungDauVao updatedNoiDungDauVao = noiDungDauVaoRepository.findById(noiDungDauVao.getId()).get();
        // Disconnect from session so that the updates on updatedNoiDungDauVao are not directly saved in db
        em.detach(updatedNoiDungDauVao);
        updatedNoiDungDauVao
            .status(UPDATED_STATUS);
        NoiDungDauVaoDTO noiDungDauVaoDTO = noiDungDauVaoMapper.toDto(updatedNoiDungDauVao);

        restNoiDungDauVaoMockMvc.perform(put("/api/noi-dung-dau-vaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDauVaoDTO)))
            .andExpect(status().isOk());

        // Validate the NoiDungDauVao in the database
        List<NoiDungDauVao> noiDungDauVaoList = noiDungDauVaoRepository.findAll();
        assertThat(noiDungDauVaoList).hasSize(databaseSizeBeforeUpdate);
        NoiDungDauVao testNoiDungDauVao = noiDungDauVaoList.get(noiDungDauVaoList.size() - 1);
        assertThat(testNoiDungDauVao.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingNoiDungDauVao() throws Exception {
        int databaseSizeBeforeUpdate = noiDungDauVaoRepository.findAll().size();

        // Create the NoiDungDauVao
        NoiDungDauVaoDTO noiDungDauVaoDTO = noiDungDauVaoMapper.toDto(noiDungDauVao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoiDungDauVaoMockMvc.perform(put("/api/noi-dung-dau-vaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noiDungDauVaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoiDungDauVao in the database
        List<NoiDungDauVao> noiDungDauVaoList = noiDungDauVaoRepository.findAll();
        assertThat(noiDungDauVaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNoiDungDauVao() throws Exception {
        // Initialize the database
        noiDungDauVaoRepository.saveAndFlush(noiDungDauVao);

        int databaseSizeBeforeDelete = noiDungDauVaoRepository.findAll().size();

        // Delete the noiDungDauVao
        restNoiDungDauVaoMockMvc.perform(delete("/api/noi-dung-dau-vaos/{id}", noiDungDauVao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NoiDungDauVao> noiDungDauVaoList = noiDungDauVaoRepository.findAll();
        assertThat(noiDungDauVaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoiDungDauVao.class);
        NoiDungDauVao noiDungDauVao1 = new NoiDungDauVao();
        noiDungDauVao1.setId(1L);
        NoiDungDauVao noiDungDauVao2 = new NoiDungDauVao();
        noiDungDauVao2.setId(noiDungDauVao1.getId());
        assertThat(noiDungDauVao1).isEqualTo(noiDungDauVao2);
        noiDungDauVao2.setId(2L);
        assertThat(noiDungDauVao1).isNotEqualTo(noiDungDauVao2);
        noiDungDauVao1.setId(null);
        assertThat(noiDungDauVao1).isNotEqualTo(noiDungDauVao2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoiDungDauVaoDTO.class);
        NoiDungDauVaoDTO noiDungDauVaoDTO1 = new NoiDungDauVaoDTO();
        noiDungDauVaoDTO1.setId(1L);
        NoiDungDauVaoDTO noiDungDauVaoDTO2 = new NoiDungDauVaoDTO();
        assertThat(noiDungDauVaoDTO1).isNotEqualTo(noiDungDauVaoDTO2);
        noiDungDauVaoDTO2.setId(noiDungDauVaoDTO1.getId());
        assertThat(noiDungDauVaoDTO1).isEqualTo(noiDungDauVaoDTO2);
        noiDungDauVaoDTO2.setId(2L);
        assertThat(noiDungDauVaoDTO1).isNotEqualTo(noiDungDauVaoDTO2);
        noiDungDauVaoDTO1.setId(null);
        assertThat(noiDungDauVaoDTO1).isNotEqualTo(noiDungDauVaoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(noiDungDauVaoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(noiDungDauVaoMapper.fromId(null)).isNull();
    }
}
