package org.constructor.web.rest;

import org.constructor.ConstructorApp;
import org.constructor.domain.GradoAcademico;
import org.constructor.repository.GradoAcademicoRepository;
import org.constructor.service.GradoAcademicoService;
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
 * Integration tests for the {@link GradoAcademicoResource} REST controller.
 */
@SpringBootTest(classes = ConstructorApp.class)
public class GradoAcademicoResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private GradoAcademicoRepository gradoAcademicoRepository;

    @Autowired
    private GradoAcademicoService gradoAcademicoService;

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

    private MockMvc restGradoAcademicoMockMvc;

    private GradoAcademico gradoAcademico;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GradoAcademicoResource gradoAcademicoResource = new GradoAcademicoResource(gradoAcademicoService);
        this.restGradoAcademicoMockMvc = MockMvcBuilders.standaloneSetup(gradoAcademicoResource)
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
    public static GradoAcademico createEntity(EntityManager em) {
        GradoAcademico gradoAcademico = new GradoAcademico()
            .descripcion(DEFAULT_DESCRIPCION);
        return gradoAcademico;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GradoAcademico createUpdatedEntity(EntityManager em) {
        GradoAcademico gradoAcademico = new GradoAcademico()
            .descripcion(UPDATED_DESCRIPCION);
        return gradoAcademico;
    }

    @BeforeEach
    public void initTest() {
        gradoAcademico = createEntity(em);
    }

    @Test
    @Transactional
    public void createGradoAcademico() throws Exception {
        int databaseSizeBeforeCreate = gradoAcademicoRepository.findAll().size();

        // Create the GradoAcademico
        restGradoAcademicoMockMvc.perform(post("/api/grado-academicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gradoAcademico)))
            .andExpect(status().isCreated());

        // Validate the GradoAcademico in the database
        List<GradoAcademico> gradoAcademicoList = gradoAcademicoRepository.findAll();
        assertThat(gradoAcademicoList).hasSize(databaseSizeBeforeCreate + 1);
        GradoAcademico testGradoAcademico = gradoAcademicoList.get(gradoAcademicoList.size() - 1);
        assertThat(testGradoAcademico.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createGradoAcademicoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gradoAcademicoRepository.findAll().size();

        // Create the GradoAcademico with an existing ID
        gradoAcademico.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGradoAcademicoMockMvc.perform(post("/api/grado-academicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gradoAcademico)))
            .andExpect(status().isBadRequest());

        // Validate the GradoAcademico in the database
        List<GradoAcademico> gradoAcademicoList = gradoAcademicoRepository.findAll();
        assertThat(gradoAcademicoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGradoAcademicos() throws Exception {
        // Initialize the database
        gradoAcademicoRepository.saveAndFlush(gradoAcademico);

        // Get all the gradoAcademicoList
        restGradoAcademicoMockMvc.perform(get("/api/grado-academicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gradoAcademico.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)));
    }
    
    @Test
    @Transactional
    public void getGradoAcademico() throws Exception {
        // Initialize the database
        gradoAcademicoRepository.saveAndFlush(gradoAcademico);

        // Get the gradoAcademico
        restGradoAcademicoMockMvc.perform(get("/api/grado-academicos/{id}", gradoAcademico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gradoAcademico.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION));
    }

    @Test
    @Transactional
    public void getNonExistingGradoAcademico() throws Exception {
        // Get the gradoAcademico
        restGradoAcademicoMockMvc.perform(get("/api/grado-academicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGradoAcademico() throws Exception {
        // Initialize the database
        gradoAcademicoService.save(gradoAcademico);

        int databaseSizeBeforeUpdate = gradoAcademicoRepository.findAll().size();

        // Update the gradoAcademico
        GradoAcademico updatedGradoAcademico = gradoAcademicoRepository.findById(gradoAcademico.getId()).get();
        // Disconnect from session so that the updates on updatedGradoAcademico are not directly saved in db
        em.detach(updatedGradoAcademico);
        updatedGradoAcademico
            .descripcion(UPDATED_DESCRIPCION);

        restGradoAcademicoMockMvc.perform(put("/api/grado-academicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGradoAcademico)))
            .andExpect(status().isOk());

        // Validate the GradoAcademico in the database
        List<GradoAcademico> gradoAcademicoList = gradoAcademicoRepository.findAll();
        assertThat(gradoAcademicoList).hasSize(databaseSizeBeforeUpdate);
        GradoAcademico testGradoAcademico = gradoAcademicoList.get(gradoAcademicoList.size() - 1);
        assertThat(testGradoAcademico.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingGradoAcademico() throws Exception {
        int databaseSizeBeforeUpdate = gradoAcademicoRepository.findAll().size();

        // Create the GradoAcademico

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGradoAcademicoMockMvc.perform(put("/api/grado-academicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gradoAcademico)))
            .andExpect(status().isBadRequest());

        // Validate the GradoAcademico in the database
        List<GradoAcademico> gradoAcademicoList = gradoAcademicoRepository.findAll();
        assertThat(gradoAcademicoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGradoAcademico() throws Exception {
        // Initialize the database
        gradoAcademicoService.save(gradoAcademico);

        int databaseSizeBeforeDelete = gradoAcademicoRepository.findAll().size();

        // Delete the gradoAcademico
        restGradoAcademicoMockMvc.perform(delete("/api/grado-academicos/{id}", gradoAcademico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GradoAcademico> gradoAcademicoList = gradoAcademicoRepository.findAll();
        assertThat(gradoAcademicoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
