package org.constructor.web.rest;

import org.constructor.ConstructorApp;
import org.constructor.domain.Ficha;
import org.constructor.repository.FichaRepository;
import org.constructor.service.FichaService;
import org.constructor.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
import java.util.ArrayList;
import java.util.List;

import static org.constructor.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FichaResource} REST controller.
 */
@SpringBootTest(classes = ConstructorApp.class)
public class FichaResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private FichaRepository fichaRepository;

    @Mock
    private FichaRepository fichaRepositoryMock;

    @Mock
    private FichaService fichaServiceMock;

    @Autowired
    private FichaService fichaService;

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

    private MockMvc restFichaMockMvc;

    private Ficha ficha;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FichaResource fichaResource = new FichaResource(fichaService);
        this.restFichaMockMvc = MockMvcBuilders.standaloneSetup(fichaResource)
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
    public static Ficha createEntity(EntityManager em) {
        Ficha ficha = new Ficha()
            .descripcion(DEFAULT_DESCRIPCION)
            .fechaCreacion(DEFAULT_FECHA_CREACION);
        return ficha;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ficha createUpdatedEntity(EntityManager em) {
        Ficha ficha = new Ficha()
            .descripcion(UPDATED_DESCRIPCION)
            .fechaCreacion(UPDATED_FECHA_CREACION);
        return ficha;
    }

    @BeforeEach
    public void initTest() {
        ficha = createEntity(em);
    }

    @Test
    @Transactional
    public void createFicha() throws Exception {
        int databaseSizeBeforeCreate = fichaRepository.findAll().size();

        // Create the Ficha
        restFichaMockMvc.perform(post("/api/fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficha)))
            .andExpect(status().isCreated());

        // Validate the Ficha in the database
        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeCreate + 1);
        Ficha testFicha = fichaList.get(fichaList.size() - 1);
        assertThat(testFicha.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testFicha.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void createFichaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fichaRepository.findAll().size();

        // Create the Ficha with an existing ID
        ficha.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFichaMockMvc.perform(post("/api/fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficha)))
            .andExpect(status().isBadRequest());

        // Validate the Ficha in the database
        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFichas() throws Exception {
        // Initialize the database
        fichaRepository.saveAndFlush(ficha);

        // Get all the fichaList
        restFichaMockMvc.perform(get("/api/fichas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ficha.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllFichasWithEagerRelationshipsIsEnabled() throws Exception {
        FichaResource fichaResource = new FichaResource(fichaServiceMock);
        when(fichaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restFichaMockMvc = MockMvcBuilders.standaloneSetup(fichaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restFichaMockMvc.perform(get("/api/fichas?eagerload=true"))
        .andExpect(status().isOk());

        verify(fichaServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllFichasWithEagerRelationshipsIsNotEnabled() throws Exception {
        FichaResource fichaResource = new FichaResource(fichaServiceMock);
            when(fichaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restFichaMockMvc = MockMvcBuilders.standaloneSetup(fichaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restFichaMockMvc.perform(get("/api/fichas?eagerload=true"))
        .andExpect(status().isOk());

            verify(fichaServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getFicha() throws Exception {
        // Initialize the database
        fichaRepository.saveAndFlush(ficha);

        // Get the ficha
        restFichaMockMvc.perform(get("/api/fichas/{id}", ficha.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ficha.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFicha() throws Exception {
        // Get the ficha
        restFichaMockMvc.perform(get("/api/fichas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFicha() throws Exception {
        // Initialize the database
        fichaService.save(ficha);

        int databaseSizeBeforeUpdate = fichaRepository.findAll().size();

        // Update the ficha
        Ficha updatedFicha = fichaRepository.findById(ficha.getId()).get();
        // Disconnect from session so that the updates on updatedFicha are not directly saved in db
        em.detach(updatedFicha);
        updatedFicha
            .descripcion(UPDATED_DESCRIPCION)
            .fechaCreacion(UPDATED_FECHA_CREACION);

        restFichaMockMvc.perform(put("/api/fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFicha)))
            .andExpect(status().isOk());

        // Validate the Ficha in the database
        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeUpdate);
        Ficha testFicha = fichaList.get(fichaList.size() - 1);
        assertThat(testFicha.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testFicha.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void updateNonExistingFicha() throws Exception {
        int databaseSizeBeforeUpdate = fichaRepository.findAll().size();

        // Create the Ficha

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFichaMockMvc.perform(put("/api/fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficha)))
            .andExpect(status().isBadRequest());

        // Validate the Ficha in the database
        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFicha() throws Exception {
        // Initialize the database
        fichaService.save(ficha);

        int databaseSizeBeforeDelete = fichaRepository.findAll().size();

        // Delete the ficha
        restFichaMockMvc.perform(delete("/api/fichas/{id}", ficha.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
