package org.constructor.web.rest;

import org.constructor.ConstructorApp;
import org.constructor.domain.NumeroGrado;
import org.constructor.repository.NumeroGradoRepository;
import org.constructor.service.NumeroGradoService;
import org.constructor.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static org.constructor.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NumeroGradoResource} REST controller.
 */
@SpringBootTest(classes = ConstructorApp.class)
public class NumeroGradoResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private NumeroGradoRepository numeroGradoRepository;

    @Autowired
    private NumeroGradoService numeroGradoService;

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

    private MockMvc restNumeroGradoMockMvc;

    private NumeroGrado numeroGrado;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NumeroGradoResource numeroGradoResource = new NumeroGradoResource(numeroGradoService);
        this.restNumeroGradoMockMvc = MockMvcBuilders.standaloneSetup(numeroGradoResource)
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
    public static NumeroGrado createEntity(EntityManager em) {
        NumeroGrado numeroGrado = new NumeroGrado()
            .descripcion(DEFAULT_DESCRIPCION);
        return numeroGrado;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NumeroGrado createUpdatedEntity(EntityManager em) {
        NumeroGrado numeroGrado = new NumeroGrado()
            .descripcion(UPDATED_DESCRIPCION);
        return numeroGrado;
    }

    @BeforeEach
    public void initTest() {
        numeroGrado = createEntity(em);
    }

    @Test
    @Transactional
    public void createNumeroGrado() throws Exception {
        int databaseSizeBeforeCreate = numeroGradoRepository.findAll().size();

        // Create the NumeroGrado
        restNumeroGradoMockMvc.perform(post("/api/numero-grados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeroGrado)))
            .andExpect(status().isCreated());

        // Validate the NumeroGrado in the database
        List<NumeroGrado> numeroGradoList = numeroGradoRepository.findAll();
        assertThat(numeroGradoList).hasSize(databaseSizeBeforeCreate + 1);
        NumeroGrado testNumeroGrado = numeroGradoList.get(numeroGradoList.size() - 1);
        assertThat(testNumeroGrado.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createNumeroGradoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = numeroGradoRepository.findAll().size();

        // Create the NumeroGrado with an existing ID
        numeroGrado.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNumeroGradoMockMvc.perform(post("/api/numero-grados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeroGrado)))
            .andExpect(status().isBadRequest());

        // Validate the NumeroGrado in the database
        List<NumeroGrado> numeroGradoList = numeroGradoRepository.findAll();
        assertThat(numeroGradoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNumeroGrados() throws Exception {
        // Initialize the database
        numeroGradoRepository.saveAndFlush(numeroGrado);

        // Get all the numeroGradoList
        restNumeroGradoMockMvc.perform(get("/api/numero-grados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(numeroGrado.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)));
    }
    
    @Test
    @Transactional
    public void getNumeroGrado() throws Exception {
        // Initialize the database
        numeroGradoRepository.saveAndFlush(numeroGrado);

        // Get the numeroGrado
        restNumeroGradoMockMvc.perform(get("/api/numero-grados/{id}", numeroGrado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(numeroGrado.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION));
    }

    @Test
    @Transactional
    public void getNonExistingNumeroGrado() throws Exception {
        // Get the numeroGrado
        restNumeroGradoMockMvc.perform(get("/api/numero-grados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNumeroGrado() throws Exception {
        // Initialize the database
        numeroGradoService.save(numeroGrado);

        int databaseSizeBeforeUpdate = numeroGradoRepository.findAll().size();

        // Update the numeroGrado
        NumeroGrado updatedNumeroGrado = numeroGradoRepository.findById(numeroGrado.getId()).get();
        // Disconnect from session so that the updates on updatedNumeroGrado are not directly saved in db
        em.detach(updatedNumeroGrado);
        updatedNumeroGrado
            .descripcion(UPDATED_DESCRIPCION);

        restNumeroGradoMockMvc.perform(put("/api/numero-grados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNumeroGrado)))
            .andExpect(status().isOk());

        // Validate the NumeroGrado in the database
        List<NumeroGrado> numeroGradoList = numeroGradoRepository.findAll();
        assertThat(numeroGradoList).hasSize(databaseSizeBeforeUpdate);
        NumeroGrado testNumeroGrado = numeroGradoList.get(numeroGradoList.size() - 1);
        assertThat(testNumeroGrado.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingNumeroGrado() throws Exception {
        int databaseSizeBeforeUpdate = numeroGradoRepository.findAll().size();

        // Create the NumeroGrado

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNumeroGradoMockMvc.perform(put("/api/numero-grados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeroGrado)))
            .andExpect(status().isBadRequest());

        // Validate the NumeroGrado in the database
        List<NumeroGrado> numeroGradoList = numeroGradoRepository.findAll();
        assertThat(numeroGradoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNumeroGrado() throws Exception {
        // Initialize the database
        numeroGradoService.save(numeroGrado);

        int databaseSizeBeforeDelete = numeroGradoRepository.findAll().size();

        // Delete the numeroGrado
        restNumeroGradoMockMvc.perform(delete("/api/numero-grados/{id}", numeroGrado.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NumeroGrado> numeroGradoList = numeroGradoRepository.findAll();
        assertThat(numeroGradoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
