package org.constructor.web.rest;

import org.constructor.ConstructorApp;
import org.constructor.domain.Version;
import org.constructor.repository.VersionRepository;
import org.constructor.service.VersionService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.constructor.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link VersionResource} REST controller.
 */
@SpringBootTest(classes = ConstructorApp.class)
public class VersionResourceIT {

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final String DEFAULT_COMENTARIO = "AAAAAAAAAA";
    private static final String UPDATED_COMENTARIO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_VERSION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_VERSION = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private VersionRepository versionRepository;

    @Autowired
    private VersionService versionService;

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

    private MockMvc restVersionMockMvc;

    private Version version;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VersionResource versionResource = new VersionResource(versionService);
        this.restVersionMockMvc = MockMvcBuilders.standaloneSetup(versionResource)
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
    public static Version createEntity(EntityManager em) {
        Version version = new Version()
            .version(DEFAULT_VERSION)
            .comentario(DEFAULT_COMENTARIO)
            .fechaVersion(DEFAULT_FECHA_VERSION);
        return version;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Version createUpdatedEntity(EntityManager em) {
        Version version = new Version()
            .version(UPDATED_VERSION)
            .comentario(UPDATED_COMENTARIO)
            .fechaVersion(UPDATED_FECHA_VERSION);
        return version;
    }

    @BeforeEach
    public void initTest() {
        version = createEntity(em);
    }

    @Test
    @Transactional
    public void createVersion() throws Exception {
        int databaseSizeBeforeCreate = versionRepository.findAll().size();

        // Create the Version
        restVersionMockMvc.perform(post("/api/versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(version)))
            .andExpect(status().isCreated());

        // Validate the Version in the database
        List<Version> versionList = versionRepository.findAll();
        assertThat(versionList).hasSize(databaseSizeBeforeCreate + 1);
        Version testVersion = versionList.get(versionList.size() - 1);
        assertThat(testVersion.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testVersion.getComentario()).isEqualTo(DEFAULT_COMENTARIO);
        assertThat(testVersion.getFechaVersion()).isEqualTo(DEFAULT_FECHA_VERSION);
    }

    @Test
    @Transactional
    public void createVersionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = versionRepository.findAll().size();

        // Create the Version with an existing ID
        version.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVersionMockMvc.perform(post("/api/versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(version)))
            .andExpect(status().isBadRequest());

        // Validate the Version in the database
        List<Version> versionList = versionRepository.findAll();
        assertThat(versionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVersions() throws Exception {
        // Initialize the database
        versionRepository.saveAndFlush(version);

        // Get all the versionList
        restVersionMockMvc.perform(get("/api/versions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(version.getId().intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].comentario").value(hasItem(DEFAULT_COMENTARIO)))
            .andExpect(jsonPath("$.[*].fechaVersion").value(hasItem(DEFAULT_FECHA_VERSION.toString())));
    }
    
    @Test
    @Transactional
    public void getVersion() throws Exception {
        // Initialize the database
        versionRepository.saveAndFlush(version);

        // Get the version
        restVersionMockMvc.perform(get("/api/versions/{id}", version.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(version.getId().intValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.comentario").value(DEFAULT_COMENTARIO))
            .andExpect(jsonPath("$.fechaVersion").value(DEFAULT_FECHA_VERSION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVersion() throws Exception {
        // Get the version
        restVersionMockMvc.perform(get("/api/versions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVersion() throws Exception {
        // Initialize the database
        versionService.save(version);

        int databaseSizeBeforeUpdate = versionRepository.findAll().size();

        // Update the version
        Version updatedVersion = versionRepository.findById(version.getId()).get();
        // Disconnect from session so that the updates on updatedVersion are not directly saved in db
        em.detach(updatedVersion);
        updatedVersion
            .version(UPDATED_VERSION)
            .comentario(UPDATED_COMENTARIO)
            .fechaVersion(UPDATED_FECHA_VERSION);

        restVersionMockMvc.perform(put("/api/versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVersion)))
            .andExpect(status().isOk());

        // Validate the Version in the database
        List<Version> versionList = versionRepository.findAll();
        assertThat(versionList).hasSize(databaseSizeBeforeUpdate);
        Version testVersion = versionList.get(versionList.size() - 1);
        assertThat(testVersion.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testVersion.getComentario()).isEqualTo(UPDATED_COMENTARIO);
        assertThat(testVersion.getFechaVersion()).isEqualTo(UPDATED_FECHA_VERSION);
    }

    @Test
    @Transactional
    public void updateNonExistingVersion() throws Exception {
        int databaseSizeBeforeUpdate = versionRepository.findAll().size();

        // Create the Version

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVersionMockMvc.perform(put("/api/versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(version)))
            .andExpect(status().isBadRequest());

        // Validate the Version in the database
        List<Version> versionList = versionRepository.findAll();
        assertThat(versionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVersion() throws Exception {
        // Initialize the database
        versionService.save(version);

        int databaseSizeBeforeDelete = versionRepository.findAll().size();

        // Delete the version
        restVersionMockMvc.perform(delete("/api/versions/{id}", version.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Version> versionList = versionRepository.findAll();
        assertThat(versionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
