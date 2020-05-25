package org.constructor.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.constructor.domain.BloqueComponentes;
import org.constructor.service.BloqueComponentesService;
import org.constructor.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing {@link org.constructor.domain.BloqueComponentes}.
 */

@RestController
@RequestMapping("/api")
public class BloqueComponentesResource {
	
	private final Logger log = LoggerFactory.getLogger(BloqueComponentesResource.class);
	
	private static final String ENTITY_NAME = "BloqueComponentes";
	
	@Value("${jhipster.clientApp.name}")
    private String applicationName;
	
	private final BloqueComponentesService bloqueComponentesService;

    public BloqueComponentesResource(BloqueComponentesService bloqueComponentesService) {
        this.bloqueComponentesService = bloqueComponentesService;
    }
    
    /**
     * {@code POST  /bloque-componentes} : Create a new BloqueComponentes.
     *
     * @param bloqueComponentes the bloqueComponentes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bloqueComponentes, or with status {@code 400 (Bad Request)} if the bloqueComponentes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bloque-componentes")
    public ResponseEntity<BloqueComponentes> createBloqueComponentes(@RequestBody BloqueComponentes bloqueComponentes) throws URISyntaxException {
        log.debug("REST request to save bloqueComponentes : {}", bloqueComponentes);
        if (bloqueComponentes.getId() != null) {
            throw new BadRequestAlertException("A new bloqueComponentes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BloqueComponentes result = bloqueComponentesService.save(bloqueComponentes);
        return ResponseEntity.created(new URI("/api/bloque-componentes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * {@code PUT  /bloque-componentes} : Updates an existing BloqueComponentes.
     *
     * @param bloqueComponentes the bloqueComponentes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bloqueComponentes,
     * or with status {@code 400 (Bad Request)} if the bloqueComponentes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bloqueComponentes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bloque-componentes")
    public ResponseEntity<BloqueComponentes> updateBloqueComponentes(@RequestBody BloqueComponentes bloqueComponentes) throws URISyntaxException {
        log.debug("REST request to update bloqueComponentes : {}", bloqueComponentes);
        if (bloqueComponentes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BloqueComponentes result = bloqueComponentesService.save(bloqueComponentes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * {@code GET  /bloque-componentes} : get all the BloqueComponentes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bloqueComponentes in body.
     */
    @GetMapping("/bloque-componentes")
    public ResponseEntity<List<BloqueComponentes>> getAllBloqueComponentes(Pageable pageable) {
        log.debug("REST request to get a page of bloqueComponentes");
        Page<BloqueComponentes> page = bloqueComponentesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    /**
     * {@code GET  /bloque-componentes/:id} : get the "id" BloqueComponentes.
     *
     * @param id the id of the BloqueComponentes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the BloqueComponentes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bloque-componentes/{id}")
    public ResponseEntity<BloqueComponentes> getBloqueComponentes(@PathVariable Long id) {
        log.debug("REST request to get BloqueComponentes : {}", id);
        Optional<BloqueComponentes> bloqueComponentes = bloqueComponentesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bloqueComponentes);
    }
    
    /**
     * {@code DELETE  /bloque-componentes/:id} : delete the "id" BloqueComponentes.
     *
     * @param id the id of the BloqueComponentes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bloque-componentes/{id}")
    public ResponseEntity<Void> deleteBloqueComponentes(@PathVariable Long id) {
        log.debug("REST request to delete BloqueComponentes : {}", id);
        bloqueComponentesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
