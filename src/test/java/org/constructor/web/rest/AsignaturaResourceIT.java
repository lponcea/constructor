package org.constructor.web.rest;

import org.constructor.ConstructorApp;
import org.constructor.domain.Asignatura;
import org.constructor.repository.AsignaturaRepository;
import org.constructor.service.AsignaturaService;
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
 * Integration tests for the {@link AsignaturaResource} REST controller.
 */
@SpringBootTest(classes = ConstructorApp.class)
public class AsignaturaResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private AsignaturaService asignaturaService;

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

    private MockMvc restAsignaturaMockMvc;

    private Asignatura asignatura;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AsignaturaResource asignaturaResource = new AsignaturaResource(asignaturaService);
        this.restAsignaturaMockMvc = MockMvcBuilders.standaloneSetup(asignaturaResource)
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
    public static Asignatura createEntity(EntityManager em) {
        Asignatura asignatura = new Asignatura()
            .descripcion(DEFAULT_DESCRIPCION);
        return asignatura;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Asignatura createUpdatedEntity(EntityManager em) {
        Asignatura asignatura = new Asignatura()
            .descripcion(UPDATED_DESCRIPCION);
        return asignatura;
    }

    @BeforeEach
    public void initTest() {
        asignatura = createEntity(em);
    }

    @Test
    @Transactional
    public void createAsignatura() throws Exception {
        int databaseSizeBeforeCreate = asignaturaRepository.findAll().size();

        // Create the Asignatura
        restAsignaturaMockMvc.perform(post("/api/asignaturas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(asignatura)))
            .andExpect(status().isCreated());

        // Validate the Asignatura in the database
        List<Asignatura> asignaturaList = asignaturaRepository.findAll();
        assertThat(asignaturaList).hasSize(databaseSizeBeforeCreate + 1);
        Asignatura testAsignatura = asignaturaList.get(asignaturaList.size() - 1);
        assertThat(testAsignatura.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createAsignaturaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = asignaturaRepository.findAll().size();

        // Create the Asignatura with an existing ID
        asignatura.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAsignaturaMockMvc.perform(post("/api/asignaturas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(asignatura)))
            .andExpect(status().isBadRequest());

        // Validate the Asignatura in the database
        List<Asignatura> asignaturaList = asignaturaRepository.findAll();
        assertThat(asignaturaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAsignaturas() throws Exception {
        // Initialize the database
        asignaturaRepository.saveAndFlush(asignatura);

        // Get all the asignaturaList
        restAsignaturaMockMvc.perform(get("/api/asignaturas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(asignatura.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)));
    }
    
    @Test
    @Transactional
    public void getAsignatura() throws Exception {
        // Initialize the database
        asignaturaRepository.saveAndFlush(asignatura);

        // Get the asignatura
        restAsignaturaMockMvc.perform(get("/api/asignaturas/{id}", asignatura.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(asignatura.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION));
    }

    @Test
    @Transactional
    public void getNonExistingAsignatura() throws Exception {
        // Get the asignatura
        restAsignaturaMockMvc.perform(get("/api/asignaturas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAsignatura() throws Exception {
        // Initialize the database
        asignaturaService.save(asignatura);

        int databaseSizeBeforeUpdate = asignaturaRepository.findAll().size();

        // Update the asignatura
        Asignatura updatedAsignatura = asignaturaRepository.findById(asignatura.getId()).get();
        // Disconnect from session so that the updates on updatedAsignatura are not directly saved in db
        em.detach(updatedAsignatura);
        updatedAsignatura
            .descripcion(UPDATED_DESCRIPCION);

        restAsignaturaMockMvc.perform(put("/api/asignaturas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAsignatura)))
            .andExpect(status().isOk());

        // Validate the Asignatura in the database
        List<Asignatura> asignaturaList = asignaturaRepository.findAll();
        assertThat(asignaturaList).hasSize(databaseSizeBeforeUpdate);
        Asignatura testAsignatura = asignaturaList.get(asignaturaList.size() - 1);
        assertThat(testAsignatura.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingAsignatura() throws Exception {
        int databaseSizeBeforeUpdate = asignaturaRepository.findAll().size();

        // Create the Asignatura

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAsignaturaMockMvc.perform(put("/api/asignaturas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(asignatura)))
            .andExpect(status().isBadRequest());

        // Validate the Asignatura in the database
        List<Asignatura> asignaturaList = asignaturaRepository.findAll();
        assertThat(asignaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAsignatura() throws Exception {
        // Initialize the database
        asignaturaService.save(asignatura);

        int databaseSizeBeforeDelete = asignaturaRepository.findAll().size();

        // Delete the asignatura
        restAsignaturaMockMvc.perform(delete("/api/asignaturas/{id}", asignatura.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Asignatura> asignaturaList = asignaturaRepository.findAll();
        assertThat(asignaturaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
