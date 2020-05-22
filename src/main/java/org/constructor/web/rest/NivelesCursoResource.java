/**
 * 
 */
package org.constructor.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


import org.constructor.domain.NivelesCurso;
import org.constructor.service.NivelesCursoService;
import org.constructor.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing NivelesCurso
 */
@RestController
@RequestMapping("/api")
public class NivelesCursoResource {
	
	/**
	 * Logger
	 */
	private final Logger log = LoggerFactory.getLogger(NivelesCursoResource.class);
	
	/**
	 * NivelesCurso
	 */
	private static final String ENTITY_NAME = "NivelesCurso";
	 
	/**
	 * applicationName
	 */
	@Value("${jhipster.clientApp.name}")
	    private String applicationName;
	
	/**
	 * NivelesCursoService
	 */
	@Autowired
	 private NivelesCursoService nivelesCursoService;
	
	/**
	 * NivelesCursoResource
	 * 
	 * @param nivelesCursoService
	 */
	public NivelesCursoResource(NivelesCursoService nivelesCursoService) {
	        this.nivelesCursoService = nivelesCursoService;
	    }

	   /**
     * {@code POST  /niveles-curso} : Create a new nivelesCurso.
     *
     * @param nivelesCurso to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nivelesCurso, 
     * or with status {@code 400 (Bad Request)} if the nivelesCurso has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/niveles-curso")
    public ResponseEntity<NivelesCurso> createNivelesCurso(@RequestBody NivelesCurso nivelesCurso) throws URISyntaxException {
        log.debug("REST request to save NivelesCurso : {}", nivelesCurso);
        if (nivelesCurso.getId() != null) {
            throw new BadRequestAlertException("A new Course Levels cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NivelesCurso result = nivelesCursoService.save(nivelesCurso);
        return ResponseEntity.created(new URI("/api/niveles-curso/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdLibro().toString()))
            .body(result);
    }
    
    
    /**
     * {@code PUT  /niveles-curso} : Updates an existing nivelesCurso.
     *
     * @param nivelesCurso the nivelesCurso to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nivelesCurso,
     * or with status {@code 400 (Bad Request)} if the nivelesCurso is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nivelesCurso couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/niveles-curso")
    public ResponseEntity<NivelesCurso> updateNivelesCurso(@RequestBody NivelesCurso nivelesCurso) throws URISyntaxException {
        log.debug("REST request to update NivelesCurso : {}", nivelesCurso);
        if (nivelesCurso.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NivelesCurso result = nivelesCursoService.save(nivelesCurso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nivelesCurso.getIdLibro().getDescripcion().toString()))
            .body(result);
    }
    
    /**
     * {@code GET  /niveles-curso} : get all the nivelesCurso.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nivelesCurso in body.
     */
    @GetMapping("/niveles-curso")
    public ResponseEntity<List<NivelesCurso>> getAllNivelesCurso(Pageable pageable) {
        log.debug("REST request to get a page of NivelesCurso");
        Page<NivelesCurso> page = nivelesCursoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    /**
     * {@code GET  /niveles-libro/:id} : get the "id" nivelesCurso.
     *
     * @param id the id of the nivelesCurso to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nivelesCurso,
     *  or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/niveles-curso/{id}")
    public ResponseEntity<NivelesCurso> getNivelNivelesCurso(@PathVariable Long id) {
        log.debug("REST request to get NivelesCurso : {}", id);
        Optional<NivelesCurso> nivelesCurso = nivelesCursoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nivelesCurso);
    }
    
    /**
     * {@code DELETE  /niveles-curso/:id} : delete the "id" nivelesCurso.
     *
     * @param id the id of the nivelesCurso to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/niveles-curso/{id}")
    public ResponseEntity<Void> deleteNivelesCurso(@PathVariable Long id) {
        log.debug("REST request to delete NivelesCurso : {}", id);
        nivelesCursoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
