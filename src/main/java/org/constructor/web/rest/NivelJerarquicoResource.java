package org.constructor.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.constructor.domain.NivelJerarquico;
import org.constructor.service.NivelJerarquicoService;
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
 * REST controller for managing {@link org.constructor.domain.NivelJerarquico}.
 */
@RestController
@RequestMapping("/api")
public class NivelJerarquicoResource {
	
	 private final Logger log = LoggerFactory.getLogger(NivelJerarquicoResource.class);

	 private static final String ENTITY_NAME = "NivelJerarquico";
	 
	 @Value("${jhipster.clientApp.name}")
	    private String applicationName;
	 
	 @Autowired
	 private NivelJerarquicoService nivelJerarquicoService;
	 
	 public NivelJerarquicoResource(NivelJerarquicoService nivelJerarquicoService) {
	        this.nivelJerarquicoService = nivelJerarquicoService;
	    }
	 
	     /**
	     * {@code POST  /nivel-jerarquico} : Create a new nivelJerarquico.
	     *
	     * @param nivelJerarquico to create.
	     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nivelJerarquico, or with status {@code 400 (Bad Request)} if the nivelJerarquico has already an ID.
	     * @throws URISyntaxException if the Location URI syntax is incorrect.
	     */
	    @PostMapping("/nivel-jerarquico")
	    public ResponseEntity<NivelJerarquico> createNivelJerarquico(@RequestBody NivelJerarquico nivelJerarquico) throws URISyntaxException {
	        log.debug("REST request to save NivelJerarquico : {}", nivelJerarquico);
	        if (nivelJerarquico.getId() != null) {
	            throw new BadRequestAlertException("A new Nivel Jerarquico cannot already have an ID", ENTITY_NAME, "idexists");
	        }
	        NivelJerarquico result = nivelJerarquicoService.save(nivelJerarquico);
	        return ResponseEntity.created(new URI("/api/roles-colaboradores/" + result.getId()))
	            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
	            .body(result);
	    }
	    
	    /**
	     * {@code PUT  /nivel-jerarquico} : Updates an existing nivelJerarquico.
	     *
	     * @param nivelJerarquico the nivelJerarquico to update.
	     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nivelJerarquico,
	     * or with status {@code 400 (Bad Request)} if the nivelJerarquico is not valid,
	     * or with status {@code 500 (Internal Server Error)} if the nivelJerarquico couldn't be updated.
	     * @throws URISyntaxException if the Location URI syntax is incorrect.
	     */
	    @PutMapping("/nivel-jerarquico")
	    public ResponseEntity<NivelJerarquico> updateNivelJerarquico(@RequestBody NivelJerarquico nivelJerarquico) throws URISyntaxException {
	        log.debug("REST request to update nivelJerarquico : {}", nivelJerarquico);
	        if (nivelJerarquico.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
	        }
	        NivelJerarquico result = nivelJerarquicoService.save(nivelJerarquico);
	        return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nivelJerarquico.getId().toString()))
	            .body(result);
	    }
	    
	    /**
	     * {@code GET  /nivel-jerarquico} : get all the nivelJerarquico.
	     *

	     * @param pageable the pagination information.

	     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nivelJerarquico in body.
	     */
	    @GetMapping("/nivel-jerarquico")
	    public ResponseEntity<List<NivelJerarquico>> getAllNivelJerarquico(Pageable pageable) {
	        log.debug("REST request to get a page of NivelJerarquico");
	        Page<NivelJerarquico> page = nivelJerarquicoService.findAll(pageable);
	        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
	        return ResponseEntity.ok().headers(headers).body(page.getContent());
	    }
	    
	    /**
	     * {@code GET  /nivel-jerarquico/:id} : get the "id" nivelJerarquico.
	     *
	     * @param id the id of the nivelJerarquico to retrieve.
	     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nivelJerarquico, or with status {@code 404 (Not Found)}.
	     */
	    @GetMapping("/nivel-jerarquico/{id}")
	    public ResponseEntity<NivelJerarquico> getNivelJerarquico(@PathVariable Long id) {
	        log.debug("REST request to get NivelJerarquico : {}", id);
	        Optional<NivelJerarquico> nivelJerarquico = nivelJerarquicoService.findOne(id);
	        return ResponseUtil.wrapOrNotFound(nivelJerarquico);
	    }
	    
	    /**
	     * {@code DELETE  /nivel-jerarquico/:id} : delete the "id" nivelJerarquico.
	     *
	     * @param id the id of the nivelJerarquico to delete.
	     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	     */
	    @DeleteMapping("/nivel-jerarquico/{id}")
	    public ResponseEntity<Void> deleteNivelJerarquico(@PathVariable Long id) {
	        log.debug("REST request to delete NivelJerarquico : {}", id);
	        nivelJerarquicoService.delete(id);
	        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
	    }
}
