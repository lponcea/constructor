package org.constructor.web.rest;

import org.constructor.ConstructorApp;
import org.constructor.domain.Colaborador;
import org.constructor.repository.ColaboradorRepository;
import org.constructor.service.ColaboradorService;
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
 * Integration tests for the {@link ColaboradorResource} REST controller.
 */
@SpringBootTest(classes = ConstructorApp.class)
public class ColaboradorResourceIT {

    private static final String DEFAULT_NOMBRES = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRES = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO_1 = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO_2 = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO_2 = "BBBBBBBBBB";

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ColaboradorService colaboradorService;

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

    private MockMvc restColaboradorMockMvc;

    private Colaborador colaborador;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ColaboradorResource colaboradorResource = new ColaboradorResource(colaboradorService);
        this.restColaboradorMockMvc = MockMvcBuilders.standaloneSetup(colaboradorResource)
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
    public static Colaborador createEntity(EntityManager em) {
        Colaborador colaborador = new Colaborador()
            .nombres(DEFAULT_NOMBRES)
            .apellido1(DEFAULT_APELLIDO_1)
            .apellido2(DEFAULT_APELLIDO_2);
        return colaborador;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Colaborador createUpdatedEntity(EntityManager em) {
        Colaborador colaborador = new Colaborador()
            .nombres(UPDATED_NOMBRES)
            .apellido1(UPDATED_APELLIDO_1)
            .apellido2(UPDATED_APELLIDO_2);
        return colaborador;
    }

    @BeforeEach
    public void initTest() {
        colaborador = createEntity(em);
    }

    @Test
    @Transactional
    public void createColaborador() throws Exception {
        int databaseSizeBeforeCreate = colaboradorRepository.findAll().size();

        // Create the Colaborador
        restColaboradorMockMvc.perform(post("/api/colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(colaborador)))
            .andExpect(status().isCreated());

        // Validate the Colaborador in the database
        List<Colaborador> colaboradorList = colaboradorRepository.findAll();
        assertThat(colaboradorList).hasSize(databaseSizeBeforeCreate + 1);
        Colaborador testColaborador = colaboradorList.get(colaboradorList.size() - 1);
        assertThat(testColaborador.getNombres()).isEqualTo(DEFAULT_NOMBRES);
        assertThat(testColaborador.getApellido1()).isEqualTo(DEFAULT_APELLIDO_1);
        assertThat(testColaborador.getApellido2()).isEqualTo(DEFAULT_APELLIDO_2);
    }

    @Test
    @Transactional
    public void createColaboradorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = colaboradorRepository.findAll().size();

        // Create the Colaborador with an existing ID
        colaborador.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restColaboradorMockMvc.perform(post("/api/colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(colaborador)))
            .andExpect(status().isBadRequest());

        // Validate the Colaborador in the database
        List<Colaborador> colaboradorList = colaboradorRepository.findAll();
        assertThat(colaboradorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllColaboradors() throws Exception {
        // Initialize the database
        colaboradorRepository.saveAndFlush(colaborador);

        // Get all the colaboradorList
        restColaboradorMockMvc.perform(get("/api/colaboradors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(colaborador.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombres").value(hasItem(DEFAULT_NOMBRES)))
            .andExpect(jsonPath("$.[*].apellido1").value(hasItem(DEFAULT_APELLIDO_1)))
            .andExpect(jsonPath("$.[*].apellido2").value(hasItem(DEFAULT_APELLIDO_2)));
    }
    
    @Test
    @Transactional
    public void getColaborador() throws Exception {
        // Initialize the database
        colaboradorRepository.saveAndFlush(colaborador);

        // Get the colaborador
        restColaboradorMockMvc.perform(get("/api/colaboradors/{id}", colaborador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(colaborador.getId().intValue()))
            .andExpect(jsonPath("$.nombres").value(DEFAULT_NOMBRES))
            .andExpect(jsonPath("$.apellido1").value(DEFAULT_APELLIDO_1))
            .andExpect(jsonPath("$.apellido2").value(DEFAULT_APELLIDO_2));
    }

    @Test
    @Transactional
    public void getNonExistingColaborador() throws Exception {
        // Get the colaborador
        restColaboradorMockMvc.perform(get("/api/colaboradors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateColaborador() throws Exception {
        // Initialize the database
        colaboradorService.save(colaborador);

        int databaseSizeBeforeUpdate = colaboradorRepository.findAll().size();

        // Update the colaborador
        Colaborador updatedColaborador = colaboradorRepository.findById(colaborador.getId()).get();
        // Disconnect from session so that the updates on updatedColaborador are not directly saved in db
        em.detach(updatedColaborador);
        updatedColaborador
            .nombres(UPDATED_NOMBRES)
            .apellido1(UPDATED_APELLIDO_1)
            .apellido2(UPDATED_APELLIDO_2);

        restColaboradorMockMvc.perform(put("/api/colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedColaborador)))
            .andExpect(status().isOk());

        // Validate the Colaborador in the database
        List<Colaborador> colaboradorList = colaboradorRepository.findAll();
        assertThat(colaboradorList).hasSize(databaseSizeBeforeUpdate);
        Colaborador testColaborador = colaboradorList.get(colaboradorList.size() - 1);
        assertThat(testColaborador.getNombres()).isEqualTo(UPDATED_NOMBRES);
        assertThat(testColaborador.getApellido1()).isEqualTo(UPDATED_APELLIDO_1);
        assertThat(testColaborador.getApellido2()).isEqualTo(UPDATED_APELLIDO_2);
    }

    @Test
    @Transactional
    public void updateNonExistingColaborador() throws Exception {
        int databaseSizeBeforeUpdate = colaboradorRepository.findAll().size();

        // Create the Colaborador

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restColaboradorMockMvc.perform(put("/api/colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(colaborador)))
            .andExpect(status().isBadRequest());

        // Validate the Colaborador in the database
        List<Colaborador> colaboradorList = colaboradorRepository.findAll();
        assertThat(colaboradorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteColaborador() throws Exception {
        // Initialize the database
        colaboradorService.save(colaborador);

        int databaseSizeBeforeDelete = colaboradorRepository.findAll().size();

        // Delete the colaborador
        restColaboradorMockMvc.perform(delete("/api/colaboradors/{id}", colaborador.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Colaborador> colaboradorList = colaboradorRepository.findAll();
        assertThat(colaboradorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
