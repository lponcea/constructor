package org.constructor.web.rest;

import org.constructor.ConstructorApp;
import org.constructor.domain.Editorial;
import org.constructor.repository.EditorialRepository;
import org.constructor.service.EditorialService;
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
 * Integration tests for the {@link EditorialResource} REST controller.
 */
@SpringBootTest(classes = ConstructorApp.class)
public class EditorialResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    @Autowired
    private EditorialRepository editorialRepository;

    @Autowired
    private EditorialService editorialService;

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

    private MockMvc restEditorialMockMvc;

    private Editorial editorial;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EditorialResource editorialResource = new EditorialResource(editorialService);
        this.restEditorialMockMvc = MockMvcBuilders.standaloneSetup(editorialResource)
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
    public static Editorial createEntity(EntityManager em) {
        Editorial editorial = new Editorial()
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION)
            .direccion(DEFAULT_DIRECCION)
            .telefono(DEFAULT_TELEFONO);
        return editorial;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Editorial createUpdatedEntity(EntityManager em) {
        Editorial editorial = new Editorial()
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .direccion(UPDATED_DIRECCION)
            .telefono(UPDATED_TELEFONO);
        return editorial;
    }

    @BeforeEach
    public void initTest() {
        editorial = createEntity(em);
    }

    @Test
    @Transactional
    public void createEditorial() throws Exception {
        int databaseSizeBeforeCreate = editorialRepository.findAll().size();

        // Create the Editorial
        restEditorialMockMvc.perform(post("/api/editorials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(editorial)))
            .andExpect(status().isCreated());

        // Validate the Editorial in the database
        List<Editorial> editorialList = editorialRepository.findAll();
        assertThat(editorialList).hasSize(databaseSizeBeforeCreate + 1);
        Editorial testEditorial = editorialList.get(editorialList.size() - 1);
        assertThat(testEditorial.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testEditorial.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testEditorial.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testEditorial.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
    }

    @Test
    @Transactional
    public void createEditorialWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = editorialRepository.findAll().size();

        // Create the Editorial with an existing ID
        editorial.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEditorialMockMvc.perform(post("/api/editorials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(editorial)))
            .andExpect(status().isBadRequest());

        // Validate the Editorial in the database
        List<Editorial> editorialList = editorialRepository.findAll();
        assertThat(editorialList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEditorials() throws Exception {
        // Initialize the database
        editorialRepository.saveAndFlush(editorial);

        // Get all the editorialList
        restEditorialMockMvc.perform(get("/api/editorials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(editorial.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)));
    }
    
    @Test
    @Transactional
    public void getEditorial() throws Exception {
        // Initialize the database
        editorialRepository.saveAndFlush(editorial);

        // Get the editorial
        restEditorialMockMvc.perform(get("/api/editorials/{id}", editorial.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(editorial.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO));
    }

    @Test
    @Transactional
    public void getNonExistingEditorial() throws Exception {
        // Get the editorial
        restEditorialMockMvc.perform(get("/api/editorials/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEditorial() throws Exception {
        // Initialize the database
        editorialService.save(editorial);

        int databaseSizeBeforeUpdate = editorialRepository.findAll().size();

        // Update the editorial
        Editorial updatedEditorial = editorialRepository.findById(editorial.getId()).get();
        // Disconnect from session so that the updates on updatedEditorial are not directly saved in db
        em.detach(updatedEditorial);
        updatedEditorial
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .direccion(UPDATED_DIRECCION)
            .telefono(UPDATED_TELEFONO);

        restEditorialMockMvc.perform(put("/api/editorials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEditorial)))
            .andExpect(status().isOk());

        // Validate the Editorial in the database
        List<Editorial> editorialList = editorialRepository.findAll();
        assertThat(editorialList).hasSize(databaseSizeBeforeUpdate);
        Editorial testEditorial = editorialList.get(editorialList.size() - 1);
        assertThat(testEditorial.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testEditorial.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testEditorial.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testEditorial.getTelefono()).isEqualTo(UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void updateNonExistingEditorial() throws Exception {
        int databaseSizeBeforeUpdate = editorialRepository.findAll().size();

        // Create the Editorial

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEditorialMockMvc.perform(put("/api/editorials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(editorial)))
            .andExpect(status().isBadRequest());

        // Validate the Editorial in the database
        List<Editorial> editorialList = editorialRepository.findAll();
        assertThat(editorialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEditorial() throws Exception {
        // Initialize the database
        editorialService.save(editorial);

        int databaseSizeBeforeDelete = editorialRepository.findAll().size();

        // Delete the editorial
        restEditorialMockMvc.perform(delete("/api/editorials/{id}", editorial.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Editorial> editorialList = editorialRepository.findAll();
        assertThat(editorialList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
