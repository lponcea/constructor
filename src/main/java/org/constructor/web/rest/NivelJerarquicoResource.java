package org.constructor.web.rest;

import java.util.List;
import java.util.Optional;

import org.constructor.domain.NivelJerarquico;
import org.constructor.response.NivelJerarquicoResponse;
import org.constructor.service.NivelJerarquicoService;
import org.constructor.service.dto.NivelJerarquicoDTO;
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
	
	/**
	 * Logger
	 */
	 private final Logger log = LoggerFactory.getLogger(NivelJerarquicoResource.class);

	 /**
	  * NivelJerarquico
	  */
	 private static final String ENTITY_NAME = "NivelJerarquico";
	 
	 /**
	  * String applicationName
	  */
	 @Value("${jhipster.clientApp.name}")
	    private String applicationName;
	 
	 /**
	  * Service
	  */
	 @Autowired
	 private NivelJerarquicoService nivelJerarquicoService;
	 
	 /**
	  * NivelJerarquicoResource
	  * 
	  * @param nivelJerarquicoService
	  */
	 public NivelJerarquicoResource(NivelJerarquicoService nivelJerarquicoService) {
	        this.nivelJerarquicoService = nivelJerarquicoService;
	    }
	 
	     /**
	     * {@code POST  /nivel-jerarquico} : Create a new nivelJerarquico.
	     *
	     * @param nivelJerarquico to create.
	     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nivelJerarquico, or with status {@code 400 (Bad Request)} if the nivelJerarquico has already an ID.
	     * @throws Exception 
	     */
	    @PostMapping("/nivel-jerarquico")
	    public ResponseEntity<NivelJerarquicoResponse> createNivelJerarquico(@RequestBody NivelJerarquicoDTO nivelJerarquicoDTO) throws Exception {
	        log.debug("REST request to save NivelJerarquico : {}", nivelJerarquicoDTO);
	        if (nivelJerarquicoDTO == null) {
	            throw new BadRequestAlertException("A new Nivel Jerarquico cannot already have an ID", ENTITY_NAME, "idexists");
	        }
	        NivelJerarquico result = nivelJerarquicoService.save(nivelJerarquicoDTO);
	        NivelJerarquicoResponse nivelJerarquico = nivelJerarquicoService.findOne(result.getId());
	        nivelJerarquico.setCursoId(nivelJerarquicoDTO.getCursoId());
	        nivelJerarquico.setOrden(nivelJerarquicoDTO.getOrden());
	        log.debug("result : {}", nivelJerarquico);
	        return ResponseUtil.wrapOrNotFound(Optional.of(nivelJerarquico));
	    }
	    
	    /**
	     * {@code PUT  /nivel-jerarquico} : Updates an existing nivelJerarquico.
	     *
	     * @param nivelJerarquico the nivelJerarquico to update.
	     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nivelJerarquico,
	     * or with status {@code 400 (Bad Request)} if the nivelJerarquico is not valid,
	     * or with status {@code 500 (Internal Server Error)} if the nivelJerarquico couldn't be updated.
	     * @throws Exception 
	     */
	    @PutMapping("/nivel-jerarquico")
	    public ResponseEntity<NivelJerarquicoResponse> updateNivelJerarquico(@RequestBody NivelJerarquicoDTO nivelJerarquicoDTO) throws Exception {
	        log.debug("REST request to update nivelJerarquico : {}", nivelJerarquicoDTO);
	        if (nivelJerarquicoDTO == null) {
	            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
	        }
	        Optional<NivelJerarquico> result = nivelJerarquicoService.updateNivelJerarquico(nivelJerarquicoDTO);
	        NivelJerarquicoResponse nivelJerarquico = nivelJerarquicoService.findOne(result.get().getId());
	        nivelJerarquico.setCursoId(nivelJerarquicoDTO.getCursoId());
	        log.debug("Update Level  : {}", nivelJerarquico);
	        return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.get().getId().toString()))
	            .body(nivelJerarquico);
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
	    public ResponseEntity<NivelJerarquicoResponse> getNivelJerarquico(@PathVariable Long id) {
	        log.debug("REST request to get NivelJerarquico : {}", id);
	        NivelJerarquicoResponse nivelJerarquico = nivelJerarquicoService.findOne(id);
	        return ResponseUtil.wrapOrNotFound(Optional.of(nivelJerarquico));
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
