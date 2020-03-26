package org.constructor.web.rest;

import org.constructor.ConstructorApp;
import org.constructor.domain.RolColaborador;
import org.constructor.repository.RolColaboradorRepository;
import org.constructor.service.RolColaboradorService;
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
 * Integration tests for the {@link RolColaboradorResource} REST controller.
 */
@SpringBootTest(classes = ConstructorApp.class)
public class RolColaboradorResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private RolColaboradorRepository rolColaboradorRepository;

    @Autowired
    private RolColaboradorService rolColaboradorService;

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

    private MockMvc restRolColaboradorMockMvc;

    private RolColaborador rolColaborador;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RolColaboradorResource rolColaboradorResource = new RolColaboradorResource(rolColaboradorService);
        this.restRolColaboradorMockMvc = MockMvcBuilders.standaloneSetup(rolColaboradorResource)
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
    public static RolColaborador createEntity(EntityManager em) {
        RolColaborador rolColaborador = new RolColaborador()
            .descripcion(DEFAULT_DESCRIPCION);
        return rolColaborador;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RolColaborador createUpdatedEntity(EntityManager em) {
        RolColaborador rolColaborador = new RolColaborador()
            .descripcion(UPDATED_DESCRIPCION);
        return rolColaborador;
    }

    @BeforeEach
    public void initTest() {
        rolColaborador = createEntity(em);
    }

    @Test
    @Transactional
    public void createRolColaborador() throws Exception {
        int databaseSizeBeforeCreate = rolColaboradorRepository.findAll().size();

        // Create the RolColaborador
        restRolColaboradorMockMvc.perform(post("/api/rol-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolColaborador)))
            .andExpect(status().isCreated());

        // Validate the RolColaborador in the database
        List<RolColaborador> rolColaboradorList = rolColaboradorRepository.findAll();
        assertThat(rolColaboradorList).hasSize(databaseSizeBeforeCreate + 1);
        RolColaborador testRolColaborador = rolColaboradorList.get(rolColaboradorList.size() - 1);
        assertThat(testRolColaborador.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createRolColaboradorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rolColaboradorRepository.findAll().size();

        // Create the RolColaborador with an existing ID
        rolColaborador.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRolColaboradorMockMvc.perform(post("/api/rol-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolColaborador)))
            .andExpect(status().isBadRequest());

        // Validate the RolColaborador in the database
        List<RolColaborador> rolColaboradorList = rolColaboradorRepository.findAll();
        assertThat(rolColaboradorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRolColaboradors() throws Exception {
        // Initialize the database
        rolColaboradorRepository.saveAndFlush(rolColaborador);

        // Get all the rolColaboradorList
        restRolColaboradorMockMvc.perform(get("/api/rol-colaboradors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rolColaborador.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)));
    }
    
    @Test
    @Transactional
    public void getRolColaborador() throws Exception {
        // Initialize the database
        rolColaboradorRepository.saveAndFlush(rolColaborador);

        // Get the rolColaborador
        restRolColaboradorMockMvc.perform(get("/api/rol-colaboradors/{id}", rolColaborador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rolColaborador.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION));
    }

    @Test
    @Transactional
    public void getNonExistingRolColaborador() throws Exception {
        // Get the rolColaborador
        restRolColaboradorMockMvc.perform(get("/api/rol-colaboradors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRolColaborador() throws Exception {
        // Initialize the database
        rolColaboradorService.save(rolColaborador);

        int databaseSizeBeforeUpdate = rolColaboradorRepository.findAll().size();

        // Update the rolColaborador
        RolColaborador updatedRolColaborador = rolColaboradorRepository.findById(rolColaborador.getId()).get();
        // Disconnect from session so that the updates on updatedRolColaborador are not directly saved in db
        em.detach(updatedRolColaborador);
        updatedRolColaborador
            .descripcion(UPDATED_DESCRIPCION);

        restRolColaboradorMockMvc.perform(put("/api/rol-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRolColaborador)))
            .andExpect(status().isOk());

        // Validate the RolColaborador in the database
        List<RolColaborador> rolColaboradorList = rolColaboradorRepository.findAll();
        assertThat(rolColaboradorList).hasSize(databaseSizeBeforeUpdate);
        RolColaborador testRolColaborador = rolColaboradorList.get(rolColaboradorList.size() - 1);
        assertThat(testRolColaborador.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingRolColaborador() throws Exception {
        int databaseSizeBeforeUpdate = rolColaboradorRepository.findAll().size();

        // Create the RolColaborador

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRolColaboradorMockMvc.perform(put("/api/rol-colaboradors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolColaborador)))
            .andExpect(status().isBadRequest());

        // Validate the RolColaborador in the database
        List<RolColaborador> rolColaboradorList = rolColaboradorRepository.findAll();
        assertThat(rolColaboradorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRolColaborador() throws Exception {
        // Initialize the database
        rolColaboradorService.save(rolColaborador);

        int databaseSizeBeforeDelete = rolColaboradorRepository.findAll().size();

        // Delete the rolColaborador
        restRolColaboradorMockMvc.perform(delete("/api/rol-colaboradors/{id}", rolColaborador.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RolColaborador> rolColaboradorList = rolColaboradorRepository.findAll();
        assertThat(rolColaboradorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
